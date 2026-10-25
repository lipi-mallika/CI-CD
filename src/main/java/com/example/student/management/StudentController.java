
package com.example.student.management;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    private final Map<Long, Student> students = new LinkedHashMap<>();
    private final AtomicLong idCounter = new AtomicLong(0);

    public StudentController() {
        addStudent(new Student(null, "Mallika", "mallika@example.com", "CSE"));
        addStudent(new Student(null, "Rahul", "rahul@example.com", "ECE"));
        addStudent(new Student(null, "Priya", "priya@example.com", "IT"));
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Long id) {
        Student student = students.get(id);

        if (student == null) {
            throw new NoSuchElementException("Student not found with ID: " + id);
        }

        return student;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        if (student.getName() == null || student.getName().isBlank()) {
            throw new IllegalArgumentException("Student name is required");
        }

        return addStudent(student);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable Long id,
            @RequestBody Student updatedStudent) {

        if (!students.containsKey(id)) {
            throw new NoSuchElementException("Student not found with ID: " + id);
        }

        if (updatedStudent.getName() == null
                || updatedStudent.getName().isBlank()) {
            throw new IllegalArgumentException("Student name is required");
        }

        Student student = new Student(
                id,
                updatedStudent.getName(),
                updatedStudent.getEmail(),
                updatedStudent.getDepartment()
        );

        students.put(id, student);
        return student;
    }

    @DeleteMapping("/{id}")
    public Map<String, String> deleteStudent(@PathVariable Long id) {
        if (students.remove(id) == null) {
            throw new NoSuchElementException("Student not found with ID: " + id);
        }

        return Map.of("message", "Student deleted successfully");
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return students.values().stream()
                .filter(student -> student.getName() != null
                        && student.getName().toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }

    @GetMapping("/count")
    public Map<String, Long> getStudentCount() {
        return Map.of("totalStudents", (long) students.size());
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    private Student addStudent(Student student) {
        Long id = idCounter.incrementAndGet();

        Student savedStudent = new Student(
                id,
                student.getName(),
                student.getEmail(),
                student.getDepartment()
        );

        students.put(id, savedStudent);
        return savedStudent;
    }

    public static class Student {
        private Long id;
        private String name;
        private String email;
        private String department;

        public Student() {
        }

        public Student(Long id, String name, String email, String department) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.department = department;
        }

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }
}

