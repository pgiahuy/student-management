package com.pgh.student_management.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "faculty")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FacultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "faculty", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<ClassEntity> classes;
}
