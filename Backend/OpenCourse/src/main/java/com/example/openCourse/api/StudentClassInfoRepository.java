package com.example.openCourse.api;
import com.example.openCourse.domain.StudentClassInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentClassInfoRepository extends JpaRepository<StudentClassInfo, Long>{
    public List<StudentClassInfo> findAllByCourseId(long CourseId);
    public List<StudentClassInfo> findAllByStudentId(long studentId);
    public StudentClassInfo findByStudentClassId(long studentCourseId);
}
