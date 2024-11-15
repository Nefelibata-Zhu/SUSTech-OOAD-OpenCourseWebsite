package com.example.openCourse.app;

import com.example.openCourse.domain.Courseware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.openCourse.service.CoursewareService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/courseware")
public class CoursewareApp {
    private final CoursewareService coursewareService;

    @Autowired
    public CoursewareApp(CoursewareService coursewareService) {
        this.coursewareService = coursewareService;
    }

    // 获取所有文件信息（以json形式）
    @GetMapping("/record")
    public List<Courseware> findAll(){
        System.out.println(coursewareService.getClass().getName());
        return coursewareService.findAll();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadCourseware(@PathVariable long id) {
        try {
            return coursewareService.getCoursewareById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // upload courseware
    @PostMapping("/upload-courseware")
    public ResponseEntity<String> uploadCourseware(
            @RequestParam("file") MultipartFile file,
            @RequestParam("coursewareInfo") Courseware coursewareInfo) {

        // 必要的检测在control层实现
        if (file == null) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        if (coursewareInfo == null) {
            return ResponseEntity.badRequest().body("Please fill the courseware's info");
        }

        // 检查文件类型为pdf/md
        String contentType = file.getContentType();
        if (!"application/pdf".equals(contentType) && !"text/markdown".equals(contentType)) {
            return ResponseEntity.badRequest().body("Please upload a PDF or MD file.");
        }

        try {
            Courseware savedCourseware = coursewareService.save(file, coursewareInfo);
            return ResponseEntity.ok("File uploaded successfully: " + savedCourseware.getFileName());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File uploaded fail: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourseware(@PathVariable Long id) {
        try {
            coursewareService.deleteById(id);
            return ResponseEntity.ok("Courseware deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error deleting courseware: " + e.getMessage());
        }
    }
}
