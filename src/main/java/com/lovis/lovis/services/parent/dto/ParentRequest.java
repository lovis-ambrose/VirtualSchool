package com.lovis.lovis.services.parent.dto;

public record ParentRequest(
        int studentId,
        String parentName,
        String email,
        String phoneNumber,
        String address
) {
}
