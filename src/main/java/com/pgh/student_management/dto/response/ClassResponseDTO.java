package com.pgh.student_management.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassResponseDTO {
    private Long id;
    private String className;

    private Long facultyId;
    private String facultyName;
}
