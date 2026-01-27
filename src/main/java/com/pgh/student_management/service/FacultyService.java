package com.pgh.student_management.service;

import com.pgh.student_management.dto.request.FacultyRequestDTO;
import com.pgh.student_management.dto.response.FacultyResponseDTO;
import com.pgh.student_management.entity.FacultyEntity;
import com.pgh.student_management.mapper.FacultyMapper;
import com.pgh.student_management.repository.FacultyRepository;
import com.pgh.student_management.utils.EntityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {
    private final FacultyRepository facultyRepository;

    public List<FacultyResponseDTO> getAllFaculties() {
        return facultyRepository.findAll().stream().map(FacultyMapper::toResponseDTO).toList();
    }

    public FacultyResponseDTO getFaculty(Long id) {
        return facultyRepository.findById(id).map(FacultyMapper::toResponseDTO).orElse(null);
    }

    public FacultyResponseDTO createFaculty (FacultyRequestDTO request) {
        FacultyEntity fac = FacultyEntity.builder()
                .email(request.getEmail())
                .name(request.getName())
                .build();

        FacultyEntity saved = facultyRepository.save(fac);
        return FacultyMapper.toResponseDTO(saved);
    }

    public FacultyResponseDTO updateFaculty(Long id, FacultyRequestDTO request) {
        FacultyEntity fac = facultyRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("faculty not found"));
        EntityUtils.copyNoNullProperties(request,fac);
        facultyRepository.save(fac);
        return FacultyMapper.toResponseDTO(fac);
    }

    public boolean deleteFaculty(Long id) {
        if(facultyRepository.existsById(id)) {
            facultyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
