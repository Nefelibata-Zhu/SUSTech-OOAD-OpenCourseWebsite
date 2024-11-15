package com.example.openCourse.service;
import com.example.openCourse.domain.CourseInfo;
import com.example.openCourse.domain.CourseKinds;
import com.example.openCourse.domain.UserR;

import java.util.List;

public interface CourseInfoService {
    public List<CourseInfo> findAllCourse();
    public CourseInfo findCourseByCourseId(long courseId);
}
