package com.example.openCourse.service;

import com.example.openCourse.domain.StudentClassInfo;

import java.util.List;

public interface StudentClassInfoService {
    public List<StudentClassInfo> findAll();
    public List<StudentClassInfo> findAllByTeacherId(long courseId);
    public List<StudentClassInfo> findAllByStudentId(long studentId);
    public void addStudentClassInfo(StudentClassInfo studentClassInfo);
}
