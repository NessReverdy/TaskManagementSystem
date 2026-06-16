package org.nessrev.authservice.service;

import org.nessrev.authservice.dto.AuthResponse;
import org.nessrev.authservice.dto.LoginRequest;
import org.nessrev.authservice.dto.RefreshRequest;
import org.nessrev.authservice.dto.RegisterRequest;

public interface AuthService {
  AuthResponse register(RegisterRequest registerRequest);
  AuthResponse login(LoginRequest loginRequest);
  void logout(String refreshToken);
  AuthResponse refresh(RefreshRequest refreshRequest);
}
