package com.example.openCourse.service;

import com.example.openCourse.api.CourseRepository;
import com.example.openCourse.api.UserRepository;
import com.example.openCourse.domain.CourseInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseInfoServiceImpl implements CourseInfoService{
    private final CourseRepository courseRepository;
    @Autowired
    public CourseInfoServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<CourseInfo> findAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public CourseInfo findCourseByCourseId(long courseId) {
        return courseRepository.findCourseByCourseId(courseId);
    }
}
