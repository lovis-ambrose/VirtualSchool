package com.lovis.lovis.exceptions.response;

public class EmailAlreadyExistsResponse extends RuntimeException {
    public EmailAlreadyExistsResponse(String message) {
        super(message);
    }
}
