package org.nessrev.authservice.auth.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nessrev.authservice.auth.controller.AuthController;
import org.nessrev.authservice.dto.AuthResponse;
import org.nessrev.authservice.dto.LoginRequest;
import org.nessrev.authservice.dto.RefreshRequest;
import org.nessrev.authservice.dto.RegisterRequest;
import org.nessrev.authservice.auth.service.impl.AuthServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {
  private final AuthServiceImpl authServiceImpl;

  public AuthControllerImpl(AuthServiceImpl authServiceImpl) {
    this.authServiceImpl = authServiceImpl;
  }

  @Override
  public ResponseEntity<AuthResponse> register(RegisterRequest registerRequest) throws JsonProcessingException {
    return ResponseEntity.ok(authServiceImpl.register(registerRequest));
  }

  @Override
  public ResponseEntity<AuthResponse> login(LoginRequest loginRequest) {
    return ResponseEntity.ok(authServiceImpl.login(loginRequest));
  }

  @Override
  public ResponseEntity<AuthResponse> refresh(RefreshRequest refreshRequest) {
    return ResponseEntity.ok(authServiceImpl.refresh(refreshRequest));
  }
}
