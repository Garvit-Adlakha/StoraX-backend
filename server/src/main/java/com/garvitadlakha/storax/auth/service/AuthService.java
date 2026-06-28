package com.garvitadlakha.storax.auth.service;

import org.springframework.stereotype.Service;

import com.garvitadlakha.storax.auth.dto.RegisterRequest;
import com.garvitadlakha.storax.auth.dto.RegisterResponse;
import com.garvitadlakha.storax.auth.service.handler.AuthRegistrationHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRegistrationHandler authRegistrationHandler;

    public RegisterResponse register(RegisterRequest request) {
        return authRegistrationHandler.register(request);
    }
}