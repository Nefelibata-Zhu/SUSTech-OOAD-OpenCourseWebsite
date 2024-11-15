package com.example.openCourse.app;

import com.example.openCourse.domain.Video;
import com.example.openCourse.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoApp {
    private final VideoService videoService;

    @Autowired
    public VideoApp(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/record")
    public List<Video> findAll() {
        return videoService.findAll();
    }

    @GetMapping("/record/{courseName}/{chapterName}")
    public List<Video> findByCourseNameAndChapterName(@PathVariable String courseName, @PathVariable String chapterName) {
        return videoService.findByCourseNameAndChapterName(courseName, chapterName);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("videoInfo") Video videoInfo) {

        // TODO: 可以加上對視頻大小的檢測
        if (file == null) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        if (videoInfo == null) {
            return ResponseEntity.badRequest().body("Please fill the video's info");
        }

        // 檢查文件類型為mp4
        String contentType = file.getContentType();
        if (!"video/mp4".equals(contentType)) {
            return ResponseEntity.badRequest().body("Please upload a MP4 file.");
        }

        try {
            videoService.save(file, videoInfo);
            return ResponseEntity.ok("Upload video successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload video failed");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadVideo(@PathVariable long id) {
        try {
            return videoService.getVideoById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVideo(@PathVariable long id) {
        try {
            videoService.deleteById(id);
            return ResponseEntity.ok("Delete video successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Delete video failed");
        }
    }

}
