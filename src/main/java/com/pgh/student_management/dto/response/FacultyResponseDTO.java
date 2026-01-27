package com.pgh.student_management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FacultyResponseDTO {
    private Long id;
    private String name;
    private String email;

}
