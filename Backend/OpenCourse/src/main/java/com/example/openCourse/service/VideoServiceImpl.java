package com.example.openCourse.service;

import com.example.openCourse.api.VideoRepository;
import com.example.openCourse.domain.Video;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;

    private static final Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    private String upload_dir = "./uploads/video/";

    public void setUpload_dir(String upload_dir){
        this.upload_dir = upload_dir;
    }

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> findAll() {
        return videoRepository.findAll();
    }

    @Override
    public List<Video> findByCourseNameAndChapterName(String courseName, String chapterName) {
        return videoRepository.findVideosByCourseNameAndChapterName(courseName, chapterName);
    }

    @Transactional
    @Override
    public Video save(MultipartFile file, Video videoInfo) throws IOException {

        String filePath = FileTools.saveMultipartFile(file, upload_dir);

        Video video = new Video(filePath, file, videoInfo);

        return videoRepository.save(video);
    }

    @Override
    public ResponseEntity<Resource> getVideoById(long id) {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid video Id:" + id));

        String filePathName = video.getFileUrl().substring(video.getFileUrl().lastIndexOf("/") + 1);
        Path filePath = Paths.get(upload_dir).resolve(filePathName);

        Resource resource = FileTools.loadFileAsResource(filePath);

        String contentType = FileTools.getContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Transactional
    @Override
    public void deleteById(long id) throws IOException {
        Video video = videoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid video Id:" + id));

        String filePathName = video.getFileUrl().substring(video.getFileUrl().lastIndexOf("/") + 1);
        Path filePath = Paths.get(upload_dir).resolve(filePathName);

        File file = new File(filePath.toString());
        if (file.delete()) {
            logger.info("Video" + video.getFileName() + "deleted successfully");
        } else {
            logger.info("Failed to delete the video: " + video.getFileName());
        }

        videoRepository.deleteById(id);
    }
}
