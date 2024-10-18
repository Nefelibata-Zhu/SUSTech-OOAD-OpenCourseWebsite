package com.example.openCourse.domain;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

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

    public Courseware() {};

    public Courseware(String url, MultipartFile file, Courseware coursewareInfo) {
        super(url, file, "courseware", LocalDateTime.now(), LocalDateTime.now());
        this.courseName = coursewareInfo.getCourseName();
        this.chapterName = coursewareInfo.getChapterName();
        this.version = coursewareInfo.version;
        this.couldDownload = coursewareInfo.couldDownload;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public boolean isCouldDownload() {
        return couldDownload;
    }

    public void setCouldDownload(boolean couldDownload) {
        this.couldDownload = couldDownload;
    }
}
