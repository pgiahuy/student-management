package com.pgh.student_management.service;

import com.pgh.student_management.dto.request.StudentRequestDTO;
import com.pgh.student_management.dto.response.StudentResponseDTO;
import com.pgh.student_management.entity.ClassEntity;
import com.pgh.student_management.entity.StudentEntity;
import com.pgh.student_management.mapper.StudentMapper;
import com.pgh.student_management.repository.ClassRepository;
import com.pgh.student_management.repository.FacultyRepository;
import com.pgh.student_management.repository.StudentRepository;
import com.pgh.student_management.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;

    public List<StudentResponseDTO> getAllStudents()    {
        return studentRepository.findAll().stream().map(StudentMapper::toResponseDTO).toList();
    }

    public  StudentResponseDTO getStudentById(String id) {
        return studentRepository.findById(id).map(StudentMapper::toResponseDTO).orElse(null);
    }

    public List<StudentResponseDTO> getStudentsByClassId(Long classId) {
        return studentRepository.findByAClass_Id(classId).stream().map(StudentMapper::toResponseDTO).toList();
    }

    public StudentResponseDTO createStudent (StudentRequestDTO request) {
        ClassEntity aClass = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        StudentEntity student = StudentEntity.builder()
                .id(request.getId())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .aClass(aClass)
                .build();

        StudentEntity saved = studentRepository.save(student);

        return StudentMapper.toResponseDTO(saved);
    }

    public StudentResponseDTO updateStudent (String id,StudentRequestDTO request) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        EntityUtils.copyNoNullProperties(request,student);

        if (request.getClassId() != null) {
            ClassEntity clazz = classRepository.findById(request.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class not found"));
            student.setAClass(clazz);
        }

        studentRepository.save(student);
        return StudentMapper.toResponseDTO(student);
    }

    public boolean deleteStudent (String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
