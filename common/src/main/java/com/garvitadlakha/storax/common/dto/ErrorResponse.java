package com.garvitadlakha.storax.common.dto;

import java.time.Instant;

import lombok.Data;

@Data
public class ErrorResponse {

    private Instant timestamp;
    private int status;
    private String error;
    private String path;
    private String trackingId;

    public ErrorResponse(int status, String error, String path, String trackingId) {
        this.timestamp = Instant.now();
        this.status = status;
        this.error = error;
        this.path = path;
        this.trackingId = trackingId;
    }
}
