package com.example.openCourse.domain;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class userKindsConverter implements AttributeConverter<userKinds, String> {

    @Override
    public String convertToDatabaseColumn(userKinds userKinds) {
        if(userKinds == null) {
            return null;
        }
        return userKinds.toString();
    }

    @Override
    public userKinds convertToEntityAttribute(String kind) {
        if(kind == null){
            return null;
        }
        return Stream.of(userKinds.values())
                .filter(c -> c.toString().equals(kind))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
