package com.pgh.student_management.mapper;

import com.pgh.student_management.dto.response.StudentResponseDTO;
import com.pgh.student_management.entity.StudentEntity;

public class StudentMapper {
    public static StudentResponseDTO toResponseDTO(StudentEntity student) {
        return StudentResponseDTO.builder()
                .id(student.getId())
                .fullName(student.getFullName())
                .email(student.getEmail())
                .classId(student.getAClass().getId())
                .className(student.getAClass().getClassName())
                .facultyId(student.getAClass().getFaculty().getId())
                .facultyName(student.getAClass().getFaculty().getName())
                .build();
    }
}
