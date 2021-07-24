package com.steps.postsapi.errorhandling.exceptions;

public class MissingRequiredParameterException extends Exception {

    private String message;

    public MissingRequiredParameterException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
