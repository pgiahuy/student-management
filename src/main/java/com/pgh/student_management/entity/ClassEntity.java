package com.pgh.student_management.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "class")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClassEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String className;

    @ManyToOne()
    @JoinColumn(name = "faculty_id", nullable = false)
    private FacultyEntity faculty;

    @OneToMany(mappedBy = "aClass", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<StudentEntity> students;
}
