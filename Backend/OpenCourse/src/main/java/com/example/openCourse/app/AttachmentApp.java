package com.example.openCourse.app;

import com.example.openCourse.domain.Attachment;
import com.example.openCourse.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/attachment")
public class AttachmentApp {
    private final AttachmentService attachmentService;

    @Autowired
    public AttachmentApp(AttachmentService attachmentService) {
        this.attachmentService = attachmentService;
    }

    @GetMapping("/record")
    public List<Attachment> findAll() {
        return attachmentService.findAll();
    }

    @GetMapping("/record/{courseName}/{chapterName}")
    public List<Attachment> findByCourseNameAndChapterName(@PathVariable String courseName, @PathVariable String chapterName) {
        return attachmentService.findByCourseNameAndChapterName(courseName, chapterName);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadAttachment(
            @RequestParam("file") MultipartFile file,
            @RequestParam("attachmentInfo") Attachment attachmentInfo) {

        if (file == null) {
            return ResponseEntity.badRequest().body("Please select a file to upload");
        }
        if (attachmentInfo == null) {
            return ResponseEntity.badRequest().body("Please fill the attachment's info");
        }

        try {
            attachmentService.save(file, attachmentInfo);
            return ResponseEntity.ok("Upload attachment successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Upload attachment failed");
        }
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadAttachment(@PathVariable long id) {
        try {
            return attachmentService.getAttachmentById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAttachment(@PathVariable long id) {
        try {
            attachmentService.deleteById(id);
            return ResponseEntity.ok("Delete attachment successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Delete attachment failed");
        }
    }
}
