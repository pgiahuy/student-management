package com.pgh.student_management.controller;

import com.pgh.student_management.dto.request.FacultyRequestDTO;
import com.pgh.student_management.dto.response.FacultyResponseDTO;
import com.pgh.student_management.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faculties")
public class FacultyController {
    private final FacultyService facultyService;

    @GetMapping
    public List<FacultyResponseDTO> getAllFaculties(){
        return facultyService.getAllFaculties();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyResponseDTO> getFacultyById(@PathVariable Long id){
        FacultyResponseDTO fac = facultyService.getFaculty(id);
        return fac != null ? ResponseEntity.ok(fac) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<FacultyResponseDTO> createFaculty(@RequestBody FacultyRequestDTO fac) {
        FacultyResponseDTO facultyResponseDTO = facultyService.createFaculty(fac);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", "/faculties/" + facultyResponseDTO.getId())
                .body(facultyResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyResponseDTO> updateFaculty(@PathVariable Long id,@RequestBody FacultyRequestDTO fac) {
        return ResponseEntity.ok(facultyService.updateFaculty(id,fac));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FacultyResponseDTO> deleteFaculty(@PathVariable Long id) {
        return facultyService.deleteFaculty(id) ?  ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
