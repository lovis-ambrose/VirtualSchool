package com.lovis.lovis.services.student;

import com.lovis.lovis.entities.guardian.Parent;
import com.lovis.lovis.entities.student.Student;
import com.lovis.lovis.exceptions.response.ApiRequestResponse;
import com.lovis.lovis.repositories.parent.ParentRepository;
import com.lovis.lovis.repositories.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final ParentRepository parentRepository;


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(@RequestParam int studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent()) {
            return student.get();
        }
        throw new ApiRequestResponse("No student found with id " + studentId);
    }

    public Student findStudentByRegistrationNumber(@RequestParam String registrationNumber) {
        Optional<Student> student = studentRepository.findByRegistrationNumber(registrationNumber);
        if (student.isPresent()) {
            return student.get();
        }
        throw new ApiRequestResponse("No student found with registration number " + registrationNumber);
    }

    public ResponseEntity<String> addStudent(@RequestBody Student student, @RequestParam int parentId) {
        // Check if the email already exists
        String email = student.getEmail();
        Optional<Student> emailExists = studentRepository.findByEmail(email);
        if (emailExists.isPresent()) {
            throw new ApiRequestResponse("Student with email " + email + " already exists");
        }

        // Fetch the parent from the database
        Optional<Parent> parent = parentRepository.findById(parentId);
        if (parent.isPresent()) {
            student.setParent(parent.get());  // Associate the student with the parent
        } else {
            throw new ApiRequestResponse("Parent with ID " + parentId + " not found");
        }

        // Save the student
        studentRepository.save(student);

        return ResponseEntity.ok("Student saved successfully");
    }

    public ResponseEntity<String> updateStudent(@RequestBody Student student, @RequestParam int studentId) {
        Optional<Student> studentExists = studentRepository.findById(studentId);
        if (studentExists.isPresent()) {
            Student existingStudent = studentExists.get();
            existingStudent.setFirstName(student.getFirstName());
            existingStudent.setLastName(student.getLastName());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setStudentNumber(student.getStudentNumber());
            existingStudent.setRegistrationNumber(student.getRegistrationNumber());
            existingStudent.setCourses(student.getCourses());
            existingStudent.setDateOfBirth(student.getDateOfBirth());
            existingStudent.setGender(student.getGender());
            existingStudent.setAddress(student.getAddress());
            existingStudent.setPhoneNumber(student.getPhoneNumber());
            existingStudent.setAttendance(student.getAttendance());
            studentRepository.save(existingStudent);
            return ResponseEntity.ok("Student updated successfully");
        }
        throw new ApiRequestResponse("No student found with id " + studentId);
    }

    public ResponseEntity<String> deleteStudent(@RequestParam int studentId) {
        Optional<Student> studentExists = studentRepository.findById(studentId);
        if (studentExists.isPresent()) {
            studentRepository.delete(studentExists.get());
            return ResponseEntity.ok("Student deleted successfully");
        }
        throw new ApiRequestResponse("No student found with id " + studentId);
    }
}
