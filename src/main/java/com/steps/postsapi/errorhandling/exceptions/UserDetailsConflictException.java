package com.steps.postsapi.errorhandling.exceptions;

public class UserDetailsConflictException extends Exception{

    private String message;

    public UserDetailsConflictException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}