package com.pgh.student_management.mapper;

import com.pgh.student_management.dto.response.FacultyResponseDTO;
import com.pgh.student_management.entity.FacultyEntity;

public class FacultyMapper {
    public static FacultyResponseDTO toResponseDTO(FacultyEntity fac) {
        return FacultyResponseDTO.builder()
                .id(fac.getId())
                .name(fac.getName())
                .email(fac.getEmail())
                .build();
    }
}
