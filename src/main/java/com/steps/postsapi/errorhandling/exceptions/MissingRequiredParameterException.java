package com.steps.postsapi.errorhandling.exceptions;

public class MissingRequiredParameterException extends RuntimeException {

    private String message;

    public MissingRequiredParameterException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
