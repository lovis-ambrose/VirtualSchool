package com.lovis.lovis.services.teacher.dto;

import com.lovis.lovis.enums.Gender;

import java.util.Date;


public record TeacherDTO (
        int teacherId,
        String firstName,
        String lastName,
        Gender gender,
        String email,
        String phoneNumber,
        Date hireDate
) {
}
