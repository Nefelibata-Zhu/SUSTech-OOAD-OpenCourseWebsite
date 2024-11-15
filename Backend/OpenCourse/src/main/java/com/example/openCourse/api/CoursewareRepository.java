package com.example.openCourse.api;

import com.example.openCourse.domain.Courseware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursewareRepository extends JpaRepository<Courseware, Long> {
    List<Courseware> searchCoursewaresByCourseNameAndChapterNameAndFileType(String courseName, String chapterName, String fileType);
    List<Courseware> deleteCoursewaresByCourseName(String courseName);
}
