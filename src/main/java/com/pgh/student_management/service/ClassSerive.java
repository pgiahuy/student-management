package com.pgh.student_management.service;

import com.pgh.student_management.dto.request.ClassRequestDTO;
import com.pgh.student_management.dto.response.ClassResponseDTO;
import com.pgh.student_management.entity.ClassEntity;
import com.pgh.student_management.entity.FacultyEntity;
import com.pgh.student_management.mapper.ClassMapper;
import com.pgh.student_management.repository.ClassRepository;
import com.pgh.student_management.repository.FacultyRepository;
import com.pgh.student_management.repository.StudentRepository;
import com.pgh.student_management.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassSerive {
    private final FacultyRepository facultyRepository;
    private final ClassRepository classRepository;

    public List<ClassResponseDTO> getClasses() {
        return classRepository.findAll().stream().map(ClassMapper::toResponseDTO).toList();
    }

    public ClassResponseDTO getClassById(Long id) {
        return classRepository.findById(id)
                .map(ClassMapper::toResponseDTO).orElse(null);
    }

    public List<ClassResponseDTO> getClassByFacultyId(Long facultyId) {
        return classRepository.findByFaculty_Id(facultyId).stream().map(ClassMapper::toResponseDTO).toList();
    }

    public ClassResponseDTO createClass(ClassRequestDTO request) {
        FacultyEntity fac = facultyRepository.findById(request.getFacultyId()).orElseThrow(()->
                new RuntimeException("Faculty Not Found"));

        ClassEntity classEntity = ClassEntity.builder()
                .className(request.getClassName())
                .faculty(fac)
                .build();

        ClassEntity saved = classRepository.save(classEntity);
        return ClassMapper.toResponseDTO(saved);

    }

    public ClassResponseDTO updateClass(Long id, ClassRequestDTO request) {
        ClassEntity c =  classRepository.findById(id).orElseThrow(()->
                new RuntimeException("Class Not Found"));

        EntityUtils.copyNoNullProperties(request, c);

        if (request.getFacultyId() != null) {
            FacultyEntity fa = facultyRepository.findById(request.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found"));
            c.setFaculty(fa);
        }

        ClassEntity saved = classRepository.save(c);
        return ClassMapper.toResponseDTO(saved);

    }

    public boolean deleteClass(Long id) {
        if (classRepository.existsById(id)) {
            classRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
