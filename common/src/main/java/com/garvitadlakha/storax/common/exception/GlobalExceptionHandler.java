package com.garvitadlakha.storax.common.exception;

import com.garvitadlakha.storax.common.config.ApiResponseBuilder;
import com.garvitadlakha.storax.common.dto.ErrorResponse;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ApiResponseBuilder apiResponseBuilder;

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException ex) {
        return apiResponseBuilder.buildErrorResponse(
                new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), "", ""),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                "",
                ""
        );
    }
}
