package com.garvitadlakha.storax.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.garvitadlakha.storax.auth.dto.RegisterRequest;
import com.garvitadlakha.storax.auth.dto.RegisterResponse;
import com.garvitadlakha.storax.auth.service.AuthService;

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
