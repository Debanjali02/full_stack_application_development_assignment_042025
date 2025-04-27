package com.student.vaccine.controller;

import com.student.vaccine.entity.Student;
import com.student.vaccine.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.addStudent(student));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadStudents(@RequestParam("file") MultipartFile file) {


        studentService.uploadStudentsFromCsv(file);
        return ResponseEntity.ok("Student data uploaded successfully.");
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedData) {
        Student existingStudent = studentService.getStudentById(id);

        existingStudent.setName(updatedData.getName());
        existingStudent.setStudentClass(updatedData.getStudentClass());
        existingStudent.setIsVaccinated(updatedData.getIsVaccinated());

        Student saved = studentService.addStudent(existingStudent); // same method used for save/update
        return ResponseEntity.ok(saved);
    }
}
