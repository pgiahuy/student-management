package com.pgh.student_management.controller;

import com.pgh.student_management.dto.request.StudentRequestDTO;
import com.pgh.student_management.dto.response.StudentResponseDTO;
import com.pgh.student_management.entity.ClassEntity;
import com.pgh.student_management.entity.StudentEntity;
import com.pgh.student_management.mapper.StudentMapper;
import com.pgh.student_management.repository.ClassRepository;
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
    private final ClassRepository classRepository;

    @GetMapping
    public List<StudentResponseDTO> getStudents() {
        return  studentRepository.findAll().stream().map(StudentMapper::toResponseDTO).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentResponseDTO createStudent(@RequestBody StudentRequestDTO request) {

        ClassEntity aClass = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));

        StudentEntity student = StudentEntity.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .aClass(aClass)
                .build();

        StudentEntity saved = studentRepository.save(student);

        return StudentMapper.toResponseDTO(saved);
    }
}
