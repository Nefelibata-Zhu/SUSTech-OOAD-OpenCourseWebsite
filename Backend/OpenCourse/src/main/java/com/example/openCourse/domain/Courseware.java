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
@DiscriminatorValue("courseware")
public class Courseware extends FileInfo {
    @Column(name = "course_name")
    private String courseName = "";
    @Column(name = "chapter_name")
    private String chapterName = ""; // eg. week1
    @Column(name = "version")
    private int version = 1; // 课件版本号
    @Column(name = "could_download")
    private boolean couldDownload = true;
    // TODO: 基於這兩個屬性改寫函數
    @Column(name = "courseware_no")
    private int coursewareNo;
    @Column(name = "current_used")
    private boolean currentUsed;

    public Courseware() {
    };

    public Courseware(String url, MultipartFile file, Courseware coursewareInfo) {
        super(url, file, "courseware");
        this.courseName = coursewareInfo.getCourseName();
        this.chapterName = coursewareInfo.getChapterName();
        this.version = coursewareInfo.version;
        this.couldDownload = coursewareInfo.couldDownload;
        this.coursewareNo = coursewareInfo.coursewareNo;
        this.currentUsed = true;
    }

}
