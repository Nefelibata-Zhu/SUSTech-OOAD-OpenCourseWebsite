package com.example.openCourse.domain;


import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("video")
public class Video extends FileInfo{
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "chapter_name")
    private String chapterName;
    // 基於YAGNI原則，不設置duration之類的屬性

    public Video(String url, MultipartFile file, Video videoInfo) {
        super(url, file, "video");
        this.courseName = videoInfo.getCourseName();
        this.chapterName = videoInfo.getChapterName();
    }
}
