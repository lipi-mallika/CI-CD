package com.example.student.management;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Student Management System!";
    }

    @GetMapping("/students")
    public String students() {
        return "Students Page";
    }

    @GetMapping("/health")
    public String health() {
        return "Application is running successfully!";
    }
}