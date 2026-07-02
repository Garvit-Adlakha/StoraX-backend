package com.garvitadlakha.storax.service.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.garvitadlakha.storax.common.dto.RegisterRequest;
import com.garvitadlakha.storax.common.dto.RegisterResponse;
import com.garvitadlakha.storax.common.exception.DuplicateResourceException;
import com.garvitadlakha.storax.jpa.User;
import com.garvitadlakha.storax.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthRegistrationHandler {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("User with email already exists");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = User.createRegisteredUser(request.getUsername(), request.getEmail(), hashedPassword);
        userRepository.save(user);

        return RegisterResponse.builder()
                .status(HttpStatus.OK)
                .message("User registered successfully")
                .userId(user.getId().toString())
                .email(request.getEmail())
                .username(request.getUsername())
                .build();
    }
}
