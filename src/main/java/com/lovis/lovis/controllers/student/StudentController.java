package com.lovis.lovis.controllers.student;

import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.services.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping(path="/school/v1/students/")
public class StudentController {
    public final StudentService studentService;

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

    @PostMapping("register/{parentId}")
    public ResponseEntity<String> registerStudent(@RequestBody Student student, @PathVariable int parentId) {
        return studentService.addStudent(student, parentId);
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
