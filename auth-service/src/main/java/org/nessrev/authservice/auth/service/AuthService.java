package org.nessrev.authservice.auth.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.nessrev.authservice.dto.AuthResponse;
import org.nessrev.authservice.dto.LoginRequest;
import org.nessrev.authservice.dto.RefreshRequest;
import org.nessrev.authservice.dto.RegisterRequest;

public interface AuthService {
  AuthResponse register(RegisterRequest registerRequest) throws JsonProcessingException;
  AuthResponse login(LoginRequest loginRequest);
  void logout(String refreshToken);
  AuthResponse refresh(RefreshRequest refreshRequest);
}
