package com.lovis.lovis.exceptions.response;

public class NotFoundResponse extends Exception{
    public NotFoundResponse(String message) {
        super(message);
    }

    public NotFoundResponse(String message, Throwable cause) {
        super(message, cause);
    }
}
