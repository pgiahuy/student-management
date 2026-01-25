package com.pgh.student_management.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {

    @GetMapping({"","/"})
    public String ShowHomePage() {
        return "index";
    }
}
