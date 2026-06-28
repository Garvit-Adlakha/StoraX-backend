package com.garvitadlakha.storax.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RegisterRequest {
    
    @Schema(description = "Username of the user", example = "john_doe")
    @NotBlank(message = "Username is required")
    private String username;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;


    @Schema(description = "Password of the user", example = "SecurePassword123")
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
