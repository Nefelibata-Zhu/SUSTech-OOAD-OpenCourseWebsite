package com.example.openCourse.domain;

import jakarta.persistence.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Entity
@Table(name = "fileInfos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "file_type", discriminatorType = DiscriminatorType.STRING)
public abstract class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "file_type", nullable = false)
    private String fileType; // eg. pdf, md
    @Column(name = "file_purpose")
    private String filePurpose; // eg. courseware

    @Column(name = "update_date")
    private LocalDateTime uploadDate;

    @Column(name = "last_modified")
    private LocalDateTime lastModified;

    public FileInfo() {
    }

    public FileInfo(String fileUrl, MultipartFile file, String filePurpose,
                    LocalDateTime uploadDate, LocalDateTime lastModified) {
        this.fileUrl = fileUrl;
        this.fileName = file.getOriginalFilename();
        this.fileType = file.getContentType();
        this.filePurpose = filePurpose;
        this.uploadDate = uploadDate;
        this.lastModified = lastModified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String url) {
        this.fileUrl = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String name) {
        this.fileName = name;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String contentType) {
        this.fileType = contentType;
    }

    public String getFilePurpose() {
        return filePurpose;
    }

    public void setFilePurpose(String purpose) {
        this.filePurpose = purpose;
    }

    public LocalDateTime getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDateTime uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDateTime lastModified) {
        this.lastModified = lastModified;
    }
}