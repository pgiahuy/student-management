package com.pgh.student_management.mapper;

import com.pgh.student_management.dto.response.ClassResponseDTO;
import com.pgh.student_management.entity.ClassEntity;

public class ClassMapper {
    public static ClassResponseDTO toResponseDTO(ClassEntity classEntity) {
        return ClassResponseDTO.builder()
                .id(classEntity.getId())
                .className(classEntity.getClassName())
                .facultyId(classEntity.getFaculty().getId())
                .facultyName(classEntity.getFaculty().getName())
                .build();
    }
}
