package com.pgh.student_management.dto.response;


import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponseDTO {
    private String id;
    private String fullName;
    private String email;

    private Long classId;
    private String className;

    private Long facultyId;
    private String facultyName;
}