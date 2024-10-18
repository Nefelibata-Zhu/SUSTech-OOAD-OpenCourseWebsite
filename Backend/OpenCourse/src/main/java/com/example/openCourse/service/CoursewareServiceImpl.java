package com.example.openCourse.service;

import com.example.openCourse.api.CoursewareRepository;
import com.example.openCourse.domain.Courseware;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
public class CoursewareServiceImpl implements CoursewareService {
    private final CoursewareRepository coursewareRepository;
    private static final Logger logger = LoggerFactory.getLogger(CoursewareServiceImpl.class);

    // TODO: configure with web.uploadPath in application.yml
    // @Value("${web.upload-path}")
    private String upload_dir = "./uploads/courseware/";

    // TODO: re-consider it. This function is used in <test>
    public void setUpload_dir(String upload_dir){
        this.upload_dir = upload_dir;
    }

    @Autowired
    public CoursewareServiceImpl(CoursewareRepository coursewareRepository) {
        this.coursewareRepository = coursewareRepository;
    }

    @Override
    public List<Courseware> findAll() {
        return coursewareRepository.findAll();
    }

    @Override
    public List<Courseware> findByCourseNameAndChapterName(String courseName, String chapterName) {
        return coursewareRepository.searchCoursewaresByCourseNameAndChapterNameAndFileType(courseName, chapterName, "courseware");
    }

    @Transactional
    @Override
    // 用户上传md/pdf类型的课件，服务器提供相应的信息，将文件保存在本地文件系统并将相关的信息（包括文件路径）保存在数据库中
    public Courseware save(MultipartFile file, Courseware coursewareInfo) throws IOException{

        // 获取路径
        Path uploadCoursewarePath = Paths.get(upload_dir);
        if (!Files.exists(uploadCoursewarePath)) {
            Files.createDirectories(uploadCoursewarePath);
        }

        // 保存文件
        String fileOriginalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + fileOriginalName;
        Path filePath = uploadCoursewarePath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        // 创建并保存Courseware对象
        Courseware courseware = new Courseware(filePath.toString(), file, coursewareInfo);

        return coursewareRepository.save(courseware);
    }

    @Override
    // 在前端点击文件名的超链接，基于传回的id从文件系统中获取文件并返回http信息
    public ResponseEntity<Resource> getCourseware(long id) {
        Courseware courseware = coursewareRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Courseware not found with id: " + id));

        String filePathName = courseware.getFileUrl().substring(courseware.getFileUrl().lastIndexOf('/') + 1);
        Path filePath = Paths.get(upload_dir).resolve(filePathName);

        Resource resource;
        try {
            resource = new UrlResource(filePath.toUri());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        String contentType;
        try {
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Transactional // 确保操作原子化
    @Override
    // 前端点击课件的删除按钮，基于传回的id删除本地和数据库中的课件/记录
    public void deleteById(long id) throws IOException {
        // 数据库查找
        Courseware courseware = coursewareRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Courseware not found with id: " + id));

        // 获取文件路径并删除文件
        String filePathName = courseware.getFileUrl().substring(courseware.getFileUrl().lastIndexOf('/') + 1);
        Path filePath = Paths.get(upload_dir).resolve(filePathName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            logger.debug("Error deleting file: " + e.getMessage());
        }

        // 数据库删除对应行
        coursewareRepository.deleteById(id);
    }

    @Override
    // 删除某个课程时将其下所有课程一并删除
    public void deleteByCourseName(String courseName) {
        List<Courseware> coursewares = coursewareRepository.deleteCoursewaresByCourseName(courseName);

        if (coursewares.isEmpty()) {
            throw new IllegalArgumentException("No courseware found with course name: " + courseName);
        }

        for (Courseware courseware: coursewares) {

            String filePathName = courseware.getFileUrl().substring(courseware.getFileUrl().lastIndexOf('/') + 1);
            Path filePath = Paths.get(upload_dir).resolve(filePathName);

            try {
                Files.deleteIfExists(filePath);
            } catch (IOException e) {
                logger.debug("Error deleting file: " + e.getMessage());
            }

            coursewareRepository.delete(courseware);
        }
    }
}
