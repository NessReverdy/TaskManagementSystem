package org.nessrev.authservice.auth.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.nessrev.authservice.dto.AuthResponse;
import org.nessrev.authservice.dto.LoginRequest;
import org.nessrev.authservice.dto.RefreshRequest;
import org.nessrev.authservice.dto.RegisterRequest;
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
  ) throws JsonProcessingException;

  @PostMapping("/login")
  ResponseEntity<AuthResponse> login(
    @Valid
    @RequestBody
    LoginRequest loginRequest
  );

  @PostMapping("/refresh")
  ResponseEntity<AuthResponse> refresh(
    @Valid
    @RequestBody
    RefreshRequest refreshRequest
  );
}
