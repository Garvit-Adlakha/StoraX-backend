package com.garvitadlakha.storax.common.config;

import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.garvitadlakha.storax.common.dto.BaseResponse;
import com.garvitadlakha.storax.common.dto.ErrorResponse;

@Component
public class ApiResponseBuilder {
    public HttpHeaders getResponseHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=utf-8");
        headers.add(HttpHeaders.PRAGMA, "no-cache");
        headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return headers;
    }

    public HttpHeaders getResponseHeaders(String trackingId) {
        HttpHeaders headers = getResponseHeaders();
        headers.add("trackingId", trackingId);
        return headers;
    }

    public ResponseEntity<BaseResponse> buildResponse(
            final BaseResponse response,
            final HttpStatus status,
            final String message) {
        response.setStatus(status);
        response.setMessage(message);
        return ResponseEntity
                .status(status)
                .cacheControl(CacheControl.noCache())
                .headers(getResponseHeaders())
                .body(response);

    }

    public ResponseEntity<ErrorResponse> buildErrorResponse(
            final ErrorResponse response,
            final HttpStatus status,
            final String message,
            final String path,
            final String trackingId) {
        response.setStatus(status.value());
        response.setError(message);
        response.setPath(path);
        response.setTrackingId(trackingId);
        return ResponseEntity
                .status(status)
                .cacheControl(CacheControl.noCache())
                .headers(getResponseHeaders(trackingId))
                .body(response);
    }
}
