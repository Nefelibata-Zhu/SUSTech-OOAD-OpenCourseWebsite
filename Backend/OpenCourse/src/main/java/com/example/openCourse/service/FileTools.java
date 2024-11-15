package com.example.openCourse.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FileTools {
    public File convertMultipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public static String saveMultipartFile(MultipartFile file, String path) throws IOException {
        Path uploadFilePath = Path.of(path);
        if (!Files.exists(uploadFilePath)) {
            Files.createDirectories(uploadFilePath);
        }

        String fileOriginalName = file.getOriginalFilename();
        String fileName = UUID.randomUUID() + "_" + fileOriginalName;
        Path filePath = uploadFilePath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);

        return filePath.toString();
    }

    public static Resource loadFileAsResource(Path filePath) {
        try {
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new RuntimeException("File not found " + filePath);
            }
        } catch (Exception e) {
            throw new RuntimeException("File not found " + filePath, e);
        }
    }

    public static String getContentType(Path filePath) {
        try {
            return Files.probeContentType(filePath);
        } catch (IOException e) {
            throw new RuntimeException("Could not determine file type.");
        }
    }

}
