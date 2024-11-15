package com.example.openCourse.api;

import com.example.openCourse.domain.Attachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
    List<Attachment> findAttachmentsByCourseNameAndChapterName(String courseName, String chapterName);
}
