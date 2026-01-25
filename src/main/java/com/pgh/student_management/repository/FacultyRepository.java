package com.pgh.student_management.repository;

import com.pgh.student_management.entity.FacultyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<FacultyEntity,Long> {
}
