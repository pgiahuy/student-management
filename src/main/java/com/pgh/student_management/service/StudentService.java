package com.pgh.student_management.service;
import com.pgh.student_management.dto.request.StudentRequestDTO;
import com.pgh.student_management.dto.response.StudentResponseDTO;
import com.pgh.student_management.entity.ClassEntity;
import com.pgh.student_management.entity.StudentEntity;
import com.pgh.student_management.mapper.StudentMapper;
import com.pgh.student_management.repository.ClassRepository;
import com.pgh.student_management.repository.StudentRepository;
import com.pgh.student_management.util.EntityUtil;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



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

    public List<StudentResponseDTO> searchStudents(String fullName, Long classId, Long facultyId) {

        Specification<StudentEntity> spec = Specification.where((root, query, cb) -> null);

        if (fullName != null && !fullName.isEmpty()) {
            spec = spec.and((root, query, cb) ->
                    cb.like(cb.lower(root.get("fullName")), "%" + fullName.toLowerCase() + "%"));
        }

        if (classId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("aClass").get("id"), classId));
        }

        if (facultyId != null) {
            spec = spec.and((root, query, cb) ->
                    cb.equal(root.get("aClass").get("faculty").get("id"), facultyId));
        }

        return studentRepository.findAll(spec)
                .stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }


    public StudentResponseDTO createStudent (StudentRequestDTO request) {
        ClassEntity aClass = classRepository.findById(request.getClassId())
                .orElseThrow(() -> new RuntimeException("Class not found"));
        StudentEntity student = StudentEntity.builder()
                .id(request.getId())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .aClass(aClass)
                .build();

        StudentEntity saved = studentRepository.save(student);

        return StudentMapper.toResponseDTO(saved);
    }

    public StudentResponseDTO updateStudent (String id,StudentRequestDTO request) {
        StudentEntity student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        EntityUtil.copyNoNullProperties(request,student);

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
