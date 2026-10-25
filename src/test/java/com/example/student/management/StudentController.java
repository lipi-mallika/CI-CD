
package com.example.student.management;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    @GetMapping("/")
    public String home() {
        return "<h1>Welcome to Student Management System</h1>"
                + "<p>Application is running successfully!</p>";
    }

    @GetMapping("/students")
    public String students() {
        return "<h1>Students Page</h1>"
                + "<p>Welcome to the Students section.</p>";
    }

    @GetMapping("/health")
    public String health() {
        return "Student Management Application is running successfully!";
    }
}

