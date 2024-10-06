package com.example.todoApp.exceptionHandler;

import com.example.todoApp.model.APIResponse;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.bind.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<APIResponse> NotFoundException(NotFoundException ex) {
        APIResponse response = new APIResponse();
        response.setStatus(HttpStatus.NOT_FOUND);
        response.setStatusCode(HttpStatus.NOT_FOUND.value());
        response.setBody(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<APIResponse> handleValidationException(ValidationException ex) {
        APIResponse response = new APIResponse();
        response.setStatus(HttpStatus.BAD_REQUEST);
        response.setStatusCode(HttpStatus.BAD_REQUEST.value());
        response.setBody(ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }

}
