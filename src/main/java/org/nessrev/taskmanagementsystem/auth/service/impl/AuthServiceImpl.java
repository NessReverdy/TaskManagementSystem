package org.nessrev.taskmanagementsystem.auth.service;

import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.auth.dto.AuthResponse;
import org.nessrev.taskmanagementsystem.auth.dto.LoginRequest;
import org.nessrev.taskmanagementsystem.auth.dto.RefreshRequest;
import org.nessrev.taskmanagementsystem.auth.dto.RegisterRequest;
import org.nessrev.taskmanagementsystem.jwt.service.JwtService;
import org.nessrev.taskmanagementsystem.jwt.userSecurity.entity.UserSecurity;
import org.nessrev.taskmanagementsystem.exception.custom.InvalidRefreshTokenException;
import org.nessrev.taskmanagementsystem.exception.custom.UserAlreadyExistsException;
import org.nessrev.taskmanagementsystem.exception.custom.UserNotFoundException;
import org.nessrev.taskmanagementsystem.exception.custom.WrongPasswordException;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.nessrev.taskmanagementsystem.user.enums.Role;
import org.nessrev.taskmanagementsystem.user.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);

        if (userRepository.existsByUsername(user.getUsername())){
            throw new UserAlreadyExistsException(user.getUsername());
        }
        userRepository.save(user);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> {
                    log.warn("User not found: {}", loginRequest.getUsername());
                    return new UserNotFoundException(loginRequest.getUsername());
                });

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            log.warn("Wrong password for user: {}", loginRequest.getUsername());
            throw new WrongPasswordException(loginRequest.getUsername());
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refresh(RefreshRequest refreshRequest) {
        String username = jwtService.extractUsername(refreshRequest.getRefreshToken());

        User user = userRepository.findByUsername(username)
                .orElseThrow();

        if (!jwtService.isTokenValid(refreshRequest.getRefreshToken(), new UserSecurity(user))) {
            throw new InvalidRefreshTokenException();
        }

        String newAccess = jwtService.generateAccessToken(user);
        String newRefresh = jwtService.generateRefreshToken(user);

        return new AuthResponse(newAccess, newRefresh);
    }
}
