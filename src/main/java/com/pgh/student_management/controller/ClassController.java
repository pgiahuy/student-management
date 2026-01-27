package com.pgh.student_management.controller;

import com.pgh.student_management.dto.request.ClassRequestDTO;
import com.pgh.student_management.dto.response.ClassResponseDTO;
import com.pgh.student_management.repository.ClassRepository;
import com.pgh.student_management.service.ClassSerive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/classes")
@Controller
@RequiredArgsConstructor
public class ClassController {

    private final ClassSerive classSerive;

    @GetMapping
    public List<ClassResponseDTO> getClasses() {
        return classSerive.getClasses();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> getClass(Long id) {
        ClassResponseDTO  c =  classSerive.getClassById(id);
        return c == null ?  ResponseEntity.notFound().build() : ResponseEntity.ok(c);
    }

    @PostMapping
    public ResponseEntity<ClassResponseDTO> createClass(@RequestBody ClassRequestDTO request) {
        ClassResponseDTO  c = classSerive.createClass(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location","/classes/"+c.getId())
                .body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> updateClass(@PathVariable Long id, @RequestBody ClassRequestDTO request) {
        return  ResponseEntity.ok(classSerive.updateClass(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> deleteClass(@PathVariable Long id) {
        return classSerive.deleteClass(id) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}
