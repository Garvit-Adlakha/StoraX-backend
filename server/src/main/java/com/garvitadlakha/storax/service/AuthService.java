package com.garvitadlakha.storax.service;

import org.springframework.stereotype.Service;

import com.garvitadlakha.storax.common.dto.RegisterRequest;
import com.garvitadlakha.storax.common.dto.RegisterResponse;
import com.garvitadlakha.storax.service.handler.AuthRegistrationHandler;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRegistrationHandler authRegistrationHandler;

    public RegisterResponse register(RegisterRequest request) {
        return authRegistrationHandler.register(request);
    }
}