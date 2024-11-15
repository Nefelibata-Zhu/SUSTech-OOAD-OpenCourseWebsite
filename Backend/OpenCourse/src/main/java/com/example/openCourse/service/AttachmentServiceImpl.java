package com.example.openCourse.service;

import com.example.openCourse.api.AttachmentRepository;
import com.example.openCourse.domain.Attachment;
import com.example.openCourse.domain.Courseware;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class AttachmentServiceImpl implements AttachmentService{
    private final AttachmentRepository attachmentRepository;

    private static final Logger logger = LoggerFactory.getLogger(AttachmentServiceImpl.class);

    private String upload_dir = "./uploads/attachment/";

    @Autowired
    public AttachmentServiceImpl(AttachmentRepository attachmentRepository) {
        this.attachmentRepository = attachmentRepository;
    }

    @Override
    public List<Attachment> findAll() {
        return attachmentRepository.findAll();
    }

    @Override
    public List<Attachment> findByCourseNameAndChapterName(String courseName, String chapterName) {
        return attachmentRepository.findAttachmentsByCourseNameAndChapterName(courseName, chapterName);
    }

    @Override
    public ResponseEntity<Resource> getAttachmentById(long id) {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found with id: " + id));

        String filePathName = attachment.getFileUrl().substring(attachment.getFileUrl().lastIndexOf("/") + 1);
        Path filePath = Paths.get(upload_dir).resolve(filePathName);

        Resource resource = FileTools.loadFileAsResource(filePath);

        String contentType = FileTools.getContentType(filePath);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Override
    public Attachment save(MultipartFile file, Attachment attachmentInfo) throws IOException {

        String filePath = FileTools.saveMultipartFile(file, upload_dir);

        Attachment attachment = new Attachment(filePath, file, attachmentInfo);

        return attachmentRepository.save(attachment);
    }

    @Override
    public void deleteById(long id) throws IOException {
        Attachment attachment = attachmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found with id: " + id));

        String filePathName = attachment.getFileUrl().substring(attachment.getFileUrl().lastIndexOf("/") + 1);
        Path filePath = Paths.get(upload_dir).resolve(filePathName);

        try {
            Files.deleteIfExists(filePath);
        } catch (IOException e) {
            logger.error("Failed to delete file: " + filePath);
            throw e;
        }

        attachmentRepository.deleteById(id);
    }
}
