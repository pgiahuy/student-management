package com.pgh.student_management.repository;

import com.pgh.student_management.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity,String>, JpaSpecificationExecutor<StudentEntity> {

}
