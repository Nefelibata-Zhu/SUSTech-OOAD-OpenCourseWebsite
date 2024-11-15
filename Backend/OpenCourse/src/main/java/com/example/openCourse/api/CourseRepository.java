package com.example.openCourse.api;

import com.example.openCourse.domain.CourseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CourseRepository extends JpaRepository<CourseInfo, Long>{
    public CourseInfo findCourseByCourseId(long courseId);


}
