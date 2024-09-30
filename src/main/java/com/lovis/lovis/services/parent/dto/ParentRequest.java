package com.lovis.lovis.services.parent.dto;

import com.lovis.lovis.enums.Gender;

public record ParentRequest(
        int studentId,
        String parentName,
        Gender gender,
        String email,
        String phoneNumber,
        String address
) {
}
