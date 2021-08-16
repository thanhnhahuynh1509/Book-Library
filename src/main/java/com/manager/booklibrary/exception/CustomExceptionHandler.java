package com.manager.booklibrary.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notFoundExceptionHandler(NotFoundException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AlreadyException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse alreadyExceptionHandler(AlreadyException ex) {
        return new ErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

}
