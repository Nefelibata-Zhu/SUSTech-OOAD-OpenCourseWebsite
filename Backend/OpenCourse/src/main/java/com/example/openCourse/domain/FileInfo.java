package com.example.openCourse.domain;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "fileInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // 设置表继承关系，并通过@Dis...Column设置表的继承
// @DiscriminatorColumn(name = "file_purpose", discriminatorType = DiscriminatorType.STRING)
@EntityListeners(AuditingEntityListener.class) // 启用审计功能
public abstract class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "file_url", nullable = false)
    private String fileUrl;
    @Column(name = "file_size")
    private Long fileSize;
    @Column(name = "file_name", nullable = false)
    private String fileName;
    @Column(name = "file_type", nullable = false)
    private String fileType; // eg. pdf, md
    @Column(name = "file_purpose")
    private String filePurpose; // eg. courseware

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date uploadDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_modified")
    private Date lastModified;

    public FileInfo() {}

    public FileInfo(String fileUrl, MultipartFile file, String filePurpose) {
        this.fileUrl = fileUrl;
        this.fileSize = file.getSize();
        this.fileName = file.getOriginalFilename();
        this.fileType = file.getContentType();
        this.filePurpose = filePurpose;
    }
}