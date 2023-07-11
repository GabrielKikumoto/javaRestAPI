package com.api.exercise.projectapi.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;

@RestControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String error = "Validation errors";
        ApplicationException appException = new ApplicationException(HttpStatus.BAD_REQUEST, error, ex);
        appException.setSubErrors(new ArrayList<>());
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
           ApplicationSubError appSubError =  new ApplicationSubError(fieldError.getField(), fieldError.getDefaultMessage());
            appException.getSubErrors().add(appSubError);
        });
        return buildResponseEntity(appException);
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        return buildResponseEntity (new ApplicationException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Object> handleExistingEmailException(SQLIntegrityConstraintViolationException ex) {
        String error = "E-mail already registered";
        return buildResponseEntity (new ApplicationException(HttpStatus.BAD_REQUEST, error, ex));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidPropertiesFormatException.class)
    public ResponseEntity<Object> handleInvalidEmailFormatException(InvalidPropertiesFormatException ex) {
        return buildResponseEntity (new ApplicationException(HttpStatus.BAD_REQUEST, ex.getMessage(), ex));
    }

    private ResponseEntity<Object> buildResponseEntity(ApplicationException apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
