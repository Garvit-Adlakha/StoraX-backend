package com.garvitadlakha.storax.common.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class RegisterResponse extends BaseResponse {

    @Schema(description = "Unique identifier of the registered user", example = "123e4567-e89b-12d3-a456-426614174000")
    private String userId;
    
    @Schema(description = "Username of the registered user", example = "john_doe")
    private String username;

    @Schema(description = "Email of the registered user", example = "john.doe@example.com")
    private String email;
    
}
