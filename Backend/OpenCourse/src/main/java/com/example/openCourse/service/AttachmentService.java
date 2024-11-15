package com.example.openCourse.service;

import com.example.openCourse.domain.Attachment;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface AttachmentService {
    public List<Attachment> findAll();
    public List<Attachment> findByCourseNameAndChapterName(String courseName, String chapterName);
    public ResponseEntity<Resource> getAttachmentById(long id);
    public Attachment save(MultipartFile file, Attachment attachmentInfo) throws IOException;
    public void deleteById(long id) throws IOException;
}
