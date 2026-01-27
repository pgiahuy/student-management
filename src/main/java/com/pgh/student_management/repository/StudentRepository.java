package com.pgh.student_management.repository;

import com.pgh.student_management.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity,String> {
    List<StudentEntity> findByAClass_Id(Long id);
}
