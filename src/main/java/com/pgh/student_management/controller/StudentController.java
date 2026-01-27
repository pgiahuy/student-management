package com.pgh.student_management.controller;

import com.pgh.student_management.dto.request.StudentRequestDTO;
import com.pgh.student_management.dto.response.StudentResponseDTO;
import com.pgh.student_management.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;


    @GetMapping
    public List<StudentResponseDTO> getStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable String id) {
        StudentResponseDTO studentResponseDTO = studentService.getStudentById(id);
        return studentResponseDTO!=null ?  ResponseEntity.ok(studentResponseDTO) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentRequestDTO request) {
        StudentResponseDTO std = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/students/" + std.getId())
                .body(std);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable String id, @RequestBody StudentRequestDTO request) {
        return ResponseEntity.ok(studentService.updateStudent(id,request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable String id) {
        return studentService.deleteStudent(id) ?  ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
