package org.nessrev.taskmanagementsystem.auth.controller;

import jakarta.validation.Valid;
import org.nessrev.taskmanagementsystem.auth.dto.AuthResponse;
import org.nessrev.taskmanagementsystem.auth.dto.LoginRequest;
import org.nessrev.taskmanagementsystem.auth.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
@Validated
@RequestMapping("/auth")
public interface AuthController {
    @PostMapping("/register")
    ResponseEntity<AuthResponse> register(
            @Valid
            @RequestBody
            RegisterRequest registerRequest
    );

    @PostMapping("/login")
    ResponseEntity<AuthResponse> login(
            @Valid
            @RequestBody
            LoginRequest loginRequest
    );
}
