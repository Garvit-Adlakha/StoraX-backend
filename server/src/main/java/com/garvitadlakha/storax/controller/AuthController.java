package com.garvitadlakha.storax.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garvitadlakha.storax.common.dto.RegisterRequest;
import com.garvitadlakha.storax.common.dto.RegisterResponse;
import com.garvitadlakha.storax.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(
        @Valid @RequestBody RegisterRequest request){
        return authService.register(request);
    }
}
