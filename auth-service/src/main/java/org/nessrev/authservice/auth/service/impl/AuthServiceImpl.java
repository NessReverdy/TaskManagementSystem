package org.nessrev.authservice.auth.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.nessrev.authservice.auth.entity.AuthUser;
import org.nessrev.authservice.auth.service.AuthService;
import org.nessrev.authservice.dto.*;
import org.nessrev.authservice.enums.OutboxEventType;
import org.nessrev.authservice.exception.custom.InvalidRefreshTokenException;
import org.nessrev.authservice.exception.custom.UserAlreadyExistsException;
import org.nessrev.authservice.exception.custom.UserNotFoundException;
import org.nessrev.authservice.exception.custom.WrongPasswordException;
import org.nessrev.authservice.jwt.service.jwt.JwtService;
import org.nessrev.authservice.jwt.service.refresh.RefreshTokenService;
import org.nessrev.authservice.jwt.userSecurity.entity.UserSecurity;
import org.nessrev.authservice.auth.repo.AuthUserRepository;
import org.nessrev.authservice.outbox.entity.OutboxEvent;
import org.nessrev.authservice.outbox.repo.OutboxRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
  private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
  private final AuthUserRepository authUserRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final RefreshTokenService refreshTokenService;
  private final OutboxRepository outboxRepository;
  private final ObjectMapper objectMapper;

  @Override
  @Transactional
  public AuthResponse register(RegisterRequest registerRequest) throws JsonProcessingException {
    AuthUser authUser = new AuthUser();
    authUser.setUsername(registerRequest.username());
    String passwordHash = passwordEncoder.encode(registerRequest.password());
    authUser.setPasswordHash(passwordHash);
    authUser.setRole(registerRequest.role());

    if (authUserRepository.existsByUsername(authUser.getUsername())) {
      throw new UserAlreadyExistsException(authUser.getUsername());
    }
    AuthUser savedUser = authUserRepository.save(authUser);

    UserCreatedEvent event = new UserCreatedEvent(
      authUser.getId(),
      authUser.getUsername(),
      authUser.getRole()
    );

    outboxRepository.save(OutboxEvent.build()
      .createdAt(LocalDateTime.now())
      .eventType(OutboxEventType.USER_CREATED)
      .payload(objectMapper.writeValueAsString(event))
      .processed(false)
      .build()
      );

    String accessToken = jwtService.generateAccessToken(savedUser);
    String refreshToken = jwtService.generateRefreshToken(savedUser);

    refreshTokenService.save(refreshToken, savedUser.getId());

    return new AuthResponse(accessToken, refreshToken);
  }

  @Override
  public AuthResponse login(LoginRequest loginRequest) {
    AuthUser authUser = authUserRepository.findByUsername(loginRequest.username())
      .orElseThrow(() -> {
        log.warn("User not found: {}", loginRequest.username());
        return new UserNotFoundException(loginRequest.username());
      });

    if (!passwordEncoder.matches(loginRequest.password(), authUser.getPasswordHash())) {
      log.warn("Wrong password for authUser: {}", loginRequest.username());
      throw new WrongPasswordException(loginRequest.username());
    }

    String accessToken = jwtService.generateAccessToken(authUser);
    String refreshToken = jwtService.generateRefreshToken(authUser);

    refreshTokenService.save(refreshToken, authUser.getId());

    return new AuthResponse(accessToken, refreshToken);
  }

  @Override
  public void logout(String refreshToken) {
    refreshTokenService.delete(refreshToken);
  }

  @Override
  public AuthResponse refresh(RefreshRequest request) {
    String oldRefresh = request.refreshToken();
    String username = jwtService.extractUsername(oldRefresh);

    AuthUser authUser = authUserRepository.findByUsername(username)
      .orElseThrow( () -> new UserNotFoundException(username));

    if (!jwtService.isTokenValid(
      oldRefresh,
      new UserSecurity(authUser)
    )) {
      throw new InvalidRefreshTokenException();
    }

    refreshTokenService.find(oldRefresh);
    String newAccess = jwtService.generateAccessToken(authUser);
    String newRefresh = jwtService.generateRefreshToken(authUser);

    refreshTokenService.delete(oldRefresh);
    refreshTokenService.save(newRefresh, authUser.getId());

    return new AuthResponse(newAccess, newRefresh);
  }
}
