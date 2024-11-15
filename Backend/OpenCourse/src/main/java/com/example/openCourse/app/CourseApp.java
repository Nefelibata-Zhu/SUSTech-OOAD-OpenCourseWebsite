package com.example.openCourse.app;

import com.example.openCourse.domain.CourseInfo;
import com.example.openCourse.domain.CourseKinds;
import com.example.openCourse.service.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseApp {
    private final CourseInfoService courseInfoService;
    @Autowired
    public CourseApp(CourseInfoService courseInfoService){this.courseInfoService = courseInfoService;}
    @GetMapping("/record")
    public List<CourseInfo> findAll(){
        return courseInfoService.findAllCourse();
    }
}
