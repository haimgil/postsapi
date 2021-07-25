package com.steps.postsapi.errorhandling.exceptions;

import com.steps.postsapi.errorhandling.errors.ApiError;
import io.micrometer.core.lang.Nullable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler({MissingRequiredParameterException.class,UserDetailsConflictException.class})
    @Nullable
    public ResponseEntity<Object> handleExceptions(Exception e, WebRequest request) throws RuntimeException{
        HttpHeaders headers = new HttpHeaders();
        HttpStatus status;
        if (e instanceof UserDetailsConflictException){
            status = HttpStatus.CONFLICT;
            return this.handleConflictException((UserDetailsConflictException) e, headers, status, request);
        }
        else if (e instanceof MissingRequiredParameterException){
            status = HttpStatus.CONFLICT;
            return this.handleMissingRequiredParameter((MissingRequiredParameterException) e, headers, status, request);
        }
        else if (e instanceof UserNotExistException) {
            status = HttpStatus.NOT_FOUND;
            return this.handleUserNotExistException((UserNotExistException) e, headers, status, request);
        } else {
            throw (RuntimeException) e;
        }
    }

    private ResponseEntity<Object> handleMissingRequiredParameter(MissingRequiredParameterException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError(status, e.getMessage(), e));
    }

    private ResponseEntity<Object> handleConflictException(UserDetailsConflictException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return buildResponseEntity(new ApiError(status, e.getMessage(), e));
    }

    private ResponseEntity<Object> handleUserNotExistException(UserNotExistException e, HttpHeaders headers, HttpStatus status, WebRequest request){
        return buildResponseEntity(new ApiError(status, e.getMessage(), e));
    }


    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request){
        String error = "Request body cannot be empty!";
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, error, ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
