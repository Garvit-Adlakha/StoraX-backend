package com.garvitadlakha.storax.common.exception;

import java.util.List;

import com.garvitadlakha.storax.common.config.ApiResponseBuilder;
import com.garvitadlakha.storax.common.dto.ErrorResponse;

import lombok.AllArgsConstructor;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ApiResponseBuilder apiResponseBuilder;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {
        List<ErrorResponse.FieldErrorResponse> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(this::toFieldErrorResponse)
                .toList();

        return apiResponseBuilder.buildErrorResponse(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Validation failed", request.getRequestURI(), "", fieldErrors),
                HttpStatus.BAD_REQUEST,
                "Validation failed",
                request.getRequestURI(),
                "");
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(
            DuplicateResourceException ex,
            HttpServletRequest request) {
        return apiResponseBuilder.buildErrorResponse(
                new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage(), request.getRequestURI(), ""),
                HttpStatus.CONFLICT,
                ex.getMessage(),
                request.getRequestURI(),
                ""
        );
    }

    private ErrorResponse.FieldErrorResponse toFieldErrorResponse(FieldError fieldError) {
        return new ErrorResponse.FieldErrorResponse(
                fieldError.getField(),
                fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Invalid value");
    }
}
