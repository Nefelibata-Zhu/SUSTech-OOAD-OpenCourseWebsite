package com.example.openCourse.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity
public class CourseInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long courseId;
    @Column(nullable = false)
    private String courseName;
    @Column(nullable = false)
    private long ownerId;
    @Column(nullable = false)
    private long auditorId;
    private CourseKinds courseKind;
    private String description;
    private String tags;
    @Column(nullable = false)
    private Date startTime;
    @Column(nullable = false)
    private Date endTime;

    public CourseInfo() {}
    public CourseInfo(String courseName, long ownerId, long auditorId, CourseKinds courseKind, String description, String tags, Date startTime, Date endTime){
        this.courseName = courseName;
        this.ownerId = ownerId;
        this.auditorId = auditorId;
        this.courseKind = courseKind;
        this.description = description;
        this.tags = tags;
        this.startTime = startTime;
        this.endTime = endTime;
    }

}
