package com.lovis.lovis.services.teacher;

import com.lovis.lovis.entities.teacher.Teacher;
import com.lovis.lovis.exceptions.response.ApiRequestResponse;
import com.lovis.lovis.exceptions.response.EmailAlreadyExistsResponse;
import com.lovis.lovis.repositories.teacher.TeacherRepository;
import com.lovis.lovis.services.teacher.dto.TeacherDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Optional<Teacher> getTeacherById(@RequestParam int teacherId) {
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        if (teacher.isEmpty()) {
            throw new ApiRequestResponse("Teacher with Id " + teacherId + " not found");
        }
        return teacher;
    }

    public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO teacher) {
        Optional<Teacher> emailExists = teacherRepository.findByEmail(teacher.email());
        if (emailExists.isPresent()) {
            throw new EmailAlreadyExistsResponse("Email already exists");
        }
        Teacher newTeacher = new Teacher();
        newTeacher.setTeacherId(teacher.teacherId());
        newTeacher.setFirstName(teacher.firstName());
        newTeacher.setLastName(teacher.lastName());
        newTeacher.setEmail(teacher.email());
        newTeacher.setGender(teacher.gender());
        newTeacher.setPhoneNumber(teacher.phoneNumber());
        newTeacher.setHireDate(teacher.hireDate());
        teacherRepository.save(newTeacher);
        return ResponseEntity.ok(newTeacher);
    }

    public ResponseEntity<Teacher> updateTeacher(@RequestBody TeacherDTO teacher, @RequestParam int teacherId) {
        Optional<Teacher> teacherExists = teacherRepository.findById(teacherId);
        if (teacherExists.isPresent()) {
            Teacher teacherToUpdate = teacherExists.get();
            teacherToUpdate.setFirstName(teacher.firstName());
            teacherToUpdate.setLastName(teacher.lastName());
            teacherToUpdate.setGender(teacher.gender());
            teacherToUpdate.setEmail(teacher.email());
            teacherToUpdate.setPhoneNumber(teacher.phoneNumber());
            teacherToUpdate.setHireDate(teacher.hireDate());
            return ResponseEntity.ok(teacherRepository.save(teacherToUpdate));
        }
        throw new ApiRequestResponse("Teacher with email " + teacher.email() + " not found");
    }

    public ResponseEntity<String> deleteTeacher(@RequestParam int teacherId) {
        Optional<Teacher> teacherExists = teacherRepository.findById(teacherId);
        if (teacherExists.isPresent()) {
            teacherRepository.delete(teacherExists.get());
            return ResponseEntity.ok("Deleted successfully");
        }
        throw new ApiRequestResponse("Teacher with id " + teacherId + " not found");
    }
}
