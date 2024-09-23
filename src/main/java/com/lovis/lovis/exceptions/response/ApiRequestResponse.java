package com.lovis.lovis.exceptions.response;

public class ApiRequestResponse extends RuntimeException{
    public ApiRequestResponse(String message) {
        super(message);
    }
}
