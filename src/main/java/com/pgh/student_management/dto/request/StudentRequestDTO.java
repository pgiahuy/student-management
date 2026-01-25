package com.pgh.student_management.dto.request;



import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRequestDTO {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private Long classId;
}
