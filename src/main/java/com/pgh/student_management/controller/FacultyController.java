package com.pgh.student_management.controller;

import com.pgh.student_management.repository.FacultyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/faculties")
public class FacultyController {
    final FacultyRepository facultyRepository;


}
