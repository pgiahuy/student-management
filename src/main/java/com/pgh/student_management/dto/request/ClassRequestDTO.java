package com.pgh.student_management.dto.request;

import com.pgh.student_management.entity.FacultyEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassRequestDTO {
    private String className;
    private Long facultyId;
}
