package com.api.exercise.projectapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApplicationException {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<ApplicationSubError> subErrors;

    private ApplicationException() {
        timestamp = LocalDateTime.now();
    }

    public ApplicationException(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApplicationException(HttpStatus status, String message, Throwable ex) {
        this();
        this.status = status;
        this.message = message;
    }
}
