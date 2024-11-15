package com.example.openCourse.service;

import com.example.openCourse.domain.Video;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
    List<Video> findAll();
    List<Video> findByCourseNameAndChapterName(String courseName, String chapterName);
    ResponseEntity<Resource> getVideoById(long id);
    Video save(MultipartFile file, Video videoInfo) throws IOException;
    void deleteById(long id) throws IOException;
}