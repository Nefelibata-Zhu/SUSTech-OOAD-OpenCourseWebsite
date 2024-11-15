package com.example.openCourse.domain;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class courseKindsConverter implements AttributeConverter<CourseKinds, String> {
    @Override
    public String convertToDatabaseColumn(CourseKinds courseKinds) {
        if(courseKinds == null) {
            return null;
        }
        return courseKinds.toString();
    }

    @Override
    public CourseKinds convertToEntityAttribute(String kind) {
        if(kind == null){
            return null;
        }
        return Stream.of(CourseKinds.values())
                .filter(c -> c.toString().equals(kind))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
