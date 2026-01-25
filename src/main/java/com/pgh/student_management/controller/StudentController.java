package com.pgh.student_management.controller;

import com.pgh.student_management.entity.StudentEntity;
import com.pgh.student_management.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @GetMapping
    public List<StudentEntity> getStudents() {
        return  studentRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentEntity createStudent(@RequestBody StudentEntity student) {
        return studentRepository.save(student);
    }
}
