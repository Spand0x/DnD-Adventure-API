package com.dndadventure.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(
        MethodArgumentNotValidException ex) {
        ObjectError objectError = ex.getBindingResult().getAllErrors().get(0);
        return new ResponseEntity<>(objectError.getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }
}
