package com.pgh.student_management.repository;

import com.pgh.student_management.entity.ClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<ClassEntity,Long> {
}
