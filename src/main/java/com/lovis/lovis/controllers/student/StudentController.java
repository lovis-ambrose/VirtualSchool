package com.lovis.lovis.controllers.student;

import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.repositories.student.StudentRepository;
import com.lovis.lovis.services.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path="/school/v1/students/")
public class StudentController {
    public final StudentService studentService;
    private final StudentRepository studentRepository;

    @GetMapping("all")
    public List<Student> findAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("studentDetailsById/{studentId}")
    public Student getStudentDetailsById(@PathVariable int studentId) {
        return studentService.getStudentById(studentId);
    }

    @GetMapping("RegistrationNumber/{registrationNumber}")
    public Student getStudentByRegistrationNumber(@PathVariable String registrationNumber) {
        return studentService.findStudentByRegistrationNumber(registrationNumber);
    }

    @PostMapping("register")
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @PutMapping("update/{studentId}")
    public ResponseEntity<String> updateStudent(@RequestBody Student student, @PathVariable int studentId) {
        return studentService.updateStudent(student, studentId);
    }

    @DeleteMapping("delete/{studentId}")
    public ResponseEntity<String> deleteStudent(@PathVariable int studentId) {
        return studentService.deleteStudent(studentId);
    }
}
