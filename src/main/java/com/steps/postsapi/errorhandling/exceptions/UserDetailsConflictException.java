package com.steps.postsapi.errorhandling.exceptions;

public class UserDetailsConflictException extends RuntimeException{

    private String message;

    public UserDetailsConflictException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
