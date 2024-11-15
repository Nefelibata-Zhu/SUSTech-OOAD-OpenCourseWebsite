package com.example.openCourse.api;

import com.example.openCourse.domain.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findVideosByCourseNameAndChapterName(String courseName, String chapterName);
}
