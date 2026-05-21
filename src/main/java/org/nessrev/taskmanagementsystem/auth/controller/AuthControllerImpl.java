package org.nessrev.taskmanagementsystem.auth.controller;

import org.nessrev.taskmanagementsystem.auth.dto.AuthResponse;
import org.nessrev.taskmanagementsystem.auth.dto.LoginRequest;
import org.nessrev.taskmanagementsystem.auth.dto.RegisterRequest;
import org.nessrev.taskmanagementsystem.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) {
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}
