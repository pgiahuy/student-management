package com.pgh.student_management.repository;

import com.pgh.student_management.dto.response.ClassResponseDTO;
import com.pgh.student_management.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClassRepository extends JpaRepository<ClassEntity,Long> {
    List<ClassEntity> findByFacultyId(Long facultyId);
}
