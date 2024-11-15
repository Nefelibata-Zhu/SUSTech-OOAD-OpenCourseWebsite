package com.example.openCourse.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
@Entity
public class StudentClassInfo {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studentClassId;

    @Column(nullable = false)
    private long studentId;

    @Column(nullable = false)
    private long courseId;

    @Column(nullable = false)
    @Getter
    @Setter
    private int isLike;

    public StudentClassInfo() {}
    public StudentClassInfo(long studentId, long courseId){
        this.studentId = studentId;
        this.courseId = courseId;
        this.isLike = 0;
    }
}
