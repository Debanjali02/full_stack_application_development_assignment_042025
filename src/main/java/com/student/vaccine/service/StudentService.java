package com.student.vaccine.service;

import com.student.vaccine.entity.Student;
import com.student.vaccine.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void uploadStudentsFromCsv(MultipartFile file) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            List<Student> students = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");
                if (tokens.length >= 3) {
                    String name = tokens[0].trim();
                    String studentClass = tokens[1].trim();
                    boolean isVaccinated = Boolean.parseBoolean(tokens[2].trim());

                    Student student = Student.builder()
                            .name(name)
                            .studentClass(studentClass)
                            .isVaccinated(isVaccinated)
                            .build();

                    students.add(student);
                }
            }
            studentRepository.saveAll(students);
        } catch (IOException e) {
            throw new RuntimeException("Failed to parse CSV file", e);
        }
    }





    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }
}
