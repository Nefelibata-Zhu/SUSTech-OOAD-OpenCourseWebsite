package com.example.openCourse.service;

import java.io.IOException;
import java.util.List;
import com.example.openCourse.domain.Courseware;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface CoursewareService {
    public List<Courseware> findAll();
    public List<Courseware> findByCourseNameAndChapterName(String courseName, String chapterName);
    public Courseware save(MultipartFile file, Courseware courseware_info) throws IOException;
    public ResponseEntity<Resource> getCourseware(long id); // 在web上点击对应的课件名，基于传入后端的id来在数据库中查找
    public void deleteById(long id) throws IOException; // 在web上直接点击delete，可以尝试使用id直接在数据库中进行操作

    public void deleteByCourseName(String courseName);
}
