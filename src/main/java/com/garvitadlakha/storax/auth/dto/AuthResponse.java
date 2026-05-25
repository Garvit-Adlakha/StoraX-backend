package com.garvitadlakha.storax.auth.dto;

import java.time.Instant;

import com.garvitadlakha.storax.common.dto.BaseResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class AuthResponse extends BaseResponse {

    @Schema(description = "JWT token for authenticated user", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
    private String token;

    public AuthResponse(HttpStatus status, String message, String token) {
        super(status, message, Instant.now());
        this.token = token;
    }
}
