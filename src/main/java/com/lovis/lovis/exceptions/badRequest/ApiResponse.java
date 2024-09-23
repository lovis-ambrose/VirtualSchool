package com.lovis.lovis.exceptions.badRequest;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

record ApiResponse(
        String errorMessage,
        ZonedDateTime timeStamp,
        HttpStatus httpStatus
) {
}
