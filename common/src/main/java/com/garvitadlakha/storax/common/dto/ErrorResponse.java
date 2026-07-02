package com.garvitadlakha.storax.common.dto;

import java.util.List;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String path;
    private String trackingId;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldErrorResponse> fieldErrors;

    public ErrorResponse(int status, String error, String path, String trackingId) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.path = path;
        this.trackingId = trackingId;
    }

    public ErrorResponse(int status, String error, String path, String trackingId,
            List<FieldErrorResponse> fieldErrors) {
        this(status, error, path, trackingId);
        this.fieldErrors = fieldErrors;
    }

    @Data
    public static class FieldErrorResponse {
        private final String field;
        private final String message;
    }
}
