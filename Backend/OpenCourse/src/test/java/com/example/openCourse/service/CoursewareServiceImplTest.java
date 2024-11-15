package com.example.openCourse.service;

import com.example.openCourse.api.CoursewareRepository;
import com.example.openCourse.domain.Courseware;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoursewareServiceImplTest {

    @Mock
    private CoursewareRepository coursewareRepository;

    @InjectMocks
    private CoursewareServiceImpl coursewareService;

    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        coursewareService = new CoursewareServiceImpl(coursewareRepository);
        // 设置上传目录为临时目录
        coursewareService.setUpload_dir(tempDir.toString() + "/");
    }

    @Test
    void testFindAll() {
        List<Courseware> expectedCoursewares = Arrays.asList(
                new Courseware(), new Courseware()
        );
        when(coursewareRepository.findAll()).thenReturn(expectedCoursewares);

        List<Courseware> actualCoursewares = coursewareService.findAll();

        assertEquals(expectedCoursewares, actualCoursewares);
        verify(coursewareRepository).findAll();
    }

    @Test
    void testFindByCourseNameAndChapterName() {
        String courseName = "Java";
        String chapterName = "Chapter 1";
        List<Courseware> expectedCoursewares = Arrays.asList(
                new Courseware(), new Courseware()
        );
        when(coursewareRepository.searchCoursewaresByCourseNameAndChapterNameAndFileType(courseName, chapterName, "courseware"))
                .thenReturn(expectedCoursewares);

        List<Courseware> actualCoursewares = coursewareService.findByCourseNameAndChapterName(courseName, chapterName);

        assertEquals(expectedCoursewares, actualCoursewares);
        verify(coursewareRepository).searchCoursewaresByCourseNameAndChapterNameAndFileType(courseName, chapterName, "courseware");
    }

    @Test
    void testSave() throws IOException {
        // 测试数据
        String originalFileName = "test.pdf";
        String content = "test content";
        MultipartFile file = new MockMultipartFile("file", originalFileName, "application/pdf", content.getBytes());
        Courseware coursewareInfo = new Courseware();
        coursewareInfo.setCourseName("Java3");
        coursewareInfo.setChapterName("Chapter 1");

        when(coursewareRepository.save(any(Courseware.class))).thenAnswer(invocation -> {
            Courseware savedCourseware = invocation.getArgument(0);
            savedCourseware.setId(1L); // 模拟数据库生成ID
            return savedCourseware;
        });

        Courseware result = coursewareService.save(file, coursewareInfo);

        // 验证结果
        assertNotNull(result);
        System.out.println("Url: " + result.getFileUrl());
        assertTrue(result.getFileUrl().endsWith(originalFileName)); // 检查是否包含原始文件名
        assertEquals(content.getBytes(StandardCharsets.UTF_8).length, result.getFileSize());
        assertEquals(originalFileName, result.getFileName());
        assertEquals("application/pdf", result.getFileType());
        assertEquals("courseware", result.getFilePurpose());
        assertEquals("Java3", result.getCourseName());
        assertEquals("Chapter 1", result.getChapterName());

        // 验证文件是否被保存
        ArgumentCaptor<Courseware> coursewareCaptor = ArgumentCaptor.forClass(Courseware.class);
        verify(coursewareRepository).save(coursewareCaptor.capture());
        Courseware capturedCourseware = coursewareCaptor.getValue();
        assertTrue(Files.exists(Paths.get(capturedCourseware.getFileUrl())));

        Files.deleteIfExists(Paths.get(capturedCourseware.getFileUrl()));
    }

    @Test
    void testGetCoursewareById() throws IOException {
        long id = 1L;
        Courseware courseware = new Courseware();
        courseware.setFileUrl("/uploads/test.txt");
        when(coursewareRepository.findById(id)).thenReturn(Optional.of(courseware));

        // 创建一个测试文件
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, "test content".getBytes());

        ResponseEntity<Resource> response = coursewareService.getCoursewareById(id);

        assertNotNull(response);
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        verify(coursewareRepository).findById(id);
    }

    @Test
    void testDeleteById() throws IOException {
        long id = 1L;
        Courseware courseware = new Courseware();
        courseware.setFileUrl("/uploads/test.txt");
        when(coursewareRepository.findById(id)).thenReturn(Optional.of(courseware));

        // 创建一个测试文件
        Path testFile = tempDir.resolve("test.txt");
        Files.write(testFile, "test content".getBytes());

        coursewareService.deleteById(id);

        verify(coursewareRepository).findById(id);
        verify(coursewareRepository).deleteById(id);
        assertFalse(Files.exists(testFile));
    }

    @Test
    void testDeleteByCourseName() throws IOException {
        String courseName = "Java";
        List<Courseware> coursewares = Arrays.asList(
                new Courseware(), new Courseware()
        );
        coursewares.get(0).setFileUrl("/uploads/test1.txt");
        coursewares.get(1).setFileUrl("/uploads/test2.txt");

        when(coursewareRepository.deleteCoursewaresByCourseName(courseName)).thenReturn(coursewares);

        // 创建测试文件
        Path testFile1 = tempDir.resolve("test1.txt");
        Path testFile2 = tempDir.resolve("test2.txt");
        Files.write(testFile1, "test content 1".getBytes());
        Files.write(testFile2, "test content 2".getBytes());

        coursewareService.deleteByCourseName(courseName);

        verify(coursewareRepository).deleteCoursewaresByCourseName(courseName);
        verify(coursewareRepository, times(2)).delete(any(Courseware.class));
        assertFalse(Files.exists(testFile1));
        assertFalse(Files.exists(testFile2));
    }
}