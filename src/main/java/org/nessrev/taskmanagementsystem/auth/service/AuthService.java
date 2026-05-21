package org.nessrev.taskmanagementsystem.auth.service;

import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.auth.dto.AuthResponse;
import org.nessrev.taskmanagementsystem.auth.dto.LoginRequest;
import org.nessrev.taskmanagementsystem.auth.dto.RegisterRequest;
import org.nessrev.taskmanagementsystem.auth.jwt.JwtService;
import org.nessrev.taskmanagementsystem.exception.UserNotFoundException;
import org.nessrev.taskmanagementsystem.exception.WrongPasswordException;
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

        userRepository.save(user);
        String token = jwtService.generateJwtToken(user);

        return new AuthResponse(token);
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

        String token = jwtService.generateJwtToken(user);
        return new AuthResponse(token);
    }
}
