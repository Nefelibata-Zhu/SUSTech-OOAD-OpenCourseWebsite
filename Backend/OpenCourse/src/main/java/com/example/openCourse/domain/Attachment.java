package com.example.openCourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("attachment")
public class Attachment extends FileInfo {
    @Column(name = "course_name")
    private String courseName = "";
    @Column(name = "chapter_name")
    private String chapterName = "";

    public Attachment() {
    }

    public Attachment(String url, MultipartFile file, Attachment attachmentInfo) {
        super(url, file, "attachment");
        this.courseName = attachmentInfo.getCourseName();
        this.chapterName = attachmentInfo.getChapterName();
    }
}
