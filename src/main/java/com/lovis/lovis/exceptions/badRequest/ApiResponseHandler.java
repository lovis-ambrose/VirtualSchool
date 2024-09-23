package com.lovis.lovis.exceptions.badRequest;

import com.lovis.lovis.exceptions.response.ApiRequestResponse;
import com.lovis.lovis.exceptions.response.NotFoundResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiResponseHandler {
    @ExceptionHandler({ApiRequestResponse.class})
    public ResponseEntity<Object> handleApiRequestException (ApiRequestResponse exception){
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiResponse apiResponse = new ApiResponse(
                exception.getMessage(),
                ZonedDateTime.now(),
                badRequest
        );
        //return response
        return new ResponseEntity<>(apiResponse, badRequest);
    }

    @ExceptionHandler(value = {NotFoundResponse.class}) // feed the exceptions into ApiRequestException class
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundResponse exception){
        //payload containing exception details
        HttpStatus badRequest = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiResponse notFound = new ApiResponse(
                exception.getMessage(),
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND
        );
        //return response
        return new ResponseEntity<>(notFound, badRequest);
    }
}
