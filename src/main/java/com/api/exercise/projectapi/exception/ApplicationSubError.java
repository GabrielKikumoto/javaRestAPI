package com.api.exercise.projectapi.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApplicationSubError {
    private String field;
    private String message;
}
