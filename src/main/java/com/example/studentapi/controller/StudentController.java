package com.example.studentapi.controller;

import com.example.studentapi.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
public class StudentController {
    // In-memory storage: all records are lost when the application restarts.
    private final List<Student> students = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        boolean idExists = students.stream().anyMatch(s -> s.getId() == student.getId());
        if (idExists) return ResponseEntity.status(HttpStatus.CONFLICT).build();
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @GetMapping
    public List<Student> getAllStudents() {
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable int id) {
        return findStudent(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable int id,
                                                  @RequestBody Student updatedStudent) {
        Optional<Student> existing = findStudent(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        Student student = existing.get();
        student.setName(updatedStudent.getName());
        student.setEmail(updatedStudent.getEmail());
        student.setCourse(updatedStudent.getCourse());
        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable int id) {
        boolean removed = students.removeIf(student -> student.getId() == id);
        return removed ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    private Optional<Student> findStudent(int id) {
        return students.stream().filter(student -> student.getId() == id).findFirst();
    }
}
