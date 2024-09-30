package com.lovis.lovis.services.student.dto;

import com.lovis.lovis.entities.student.Student;

import java.util.Date;

public record StudentRequest(
        int studentId,
        Integer parentId,  // Use Integer to allow null values
        String parentName,
        int studentNumber,
        String registrationNumber,
        String firstName,
        String lastName,
        String email,
        Date dateOfBirth,
        String gender,
        String address,
        String phoneNumber
) {
    public static StudentRequest fromStudentDTO(Student student) {
        // Check if the student has a parent
        Integer parentId = (student.getParent() != null) ? student.getParent().getParentId() : null;
        String parentName = (student.getParent() != null) ? student.getParent().getParentName() : null;

        return new StudentRequest(
                student.getStudentId(),
                parentId,
                parentName,
                student.getStudentNumber(),
                student.getRegistrationNumber(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getDateOfBirth(),
                student.getGender().toString(),
                student.getAddress(),
                student.getPhoneNumber()
        );
    }
}
