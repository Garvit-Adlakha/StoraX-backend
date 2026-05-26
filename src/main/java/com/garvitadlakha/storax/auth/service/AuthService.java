package com.garvitadlakha.storax.auth.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.garvitadlakha.storax.auth.dto.RegisterRequest;
import com.garvitadlakha.storax.auth.dto.RegisterResponse;
import com.garvitadlakha.storax.user.entity.User;
import com.garvitadlakha.storax.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public RegisterResponse register(RegisterRequest request) {
        
        Boolean existingUser=userRepository.existsByEmail(request.getEmail());

        if(existingUser){
            throw new RuntimeException("User with email already exists");
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