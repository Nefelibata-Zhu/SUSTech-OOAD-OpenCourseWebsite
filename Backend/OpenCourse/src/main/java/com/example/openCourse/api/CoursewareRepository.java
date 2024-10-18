package com.example.openCourse.api;

import com.example.openCourse.domain.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursewareRepository extends JpaRepository<Courseware, Long> {
    List<Courseware> searchCoursewaresByCourseNameAndChapterNameAndFileType(String courseName, String chapterName, String fileType);
    List<Courseware> deleteCoursewaresByCourseName(String courseName);
}
