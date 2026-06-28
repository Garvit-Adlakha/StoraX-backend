package com.garvitadlakha.storax.common.dto;

import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseResponse {
    
    @Schema(description = "HTTP status of the response", example = "OK")
    @JsonProperty("status")
    private HttpStatus status;

    @Schema(description = "Message describing the response", example = "Operation successful")
    @JsonProperty("message")
    private String message;

    @Schema(description = "Timestamp of when the response was generated", example = "2024-06-01T12:00:00Z")
    @JsonProperty("timestamp")
    @Builder.Default
    private Instant timestamp=Instant.now();
}
