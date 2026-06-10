package org.nessrev.userservice.service.impl;

import lombok.AllArgsConstructor;
import org.nessrev.userservice.dto.UserRequest;
import org.nessrev.userservice.dto.UserResponse;
import org.nessrev.userservice.entity.User;
import org.nessrev.userservice.enums.Role;
import org.nessrev.userservice.exception.custom.UserAlreadyExistsException;
import org.nessrev.userservice.exception.custom.UserNotFoundException;
import org.nessrev.userservice.mapper.UserMapper;
import org.nessrev.userservice.repo.UserRepository;
import org.nessrev.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
  private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  @Override
  @Transactional
  public UserResponse createUser(UserRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      log.warn("User is already exist");
      throw new UserAlreadyExistsException(request.getUsername());
    }

    String encodedPassword = passwordEncoder.encode(request.getPassword());
    User user = userMapper.toEntity(request);
    user.setPassword(encodedPassword);
    userRepository.save(user);

    log.info("User created with id {}", user.getId());
    return userMapper.toResponse(user);
  }

  @Override
  @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
  @Transactional
  public UserResponse updateUser(Long id, UserRequest request) {
    User user = userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));

    userMapper.updateUserFromDto(request, user);

    if (request.getPassword() != null) {
      user.setPassword(passwordEncoder.encode(request.getPassword()));
    }

    return userMapper.toResponse(userRepository.save(user));
  }

  @Override
  public UserResponse getUserById(Long id) {
    User user = getUserEntityById(id);
    return userMapper.toResponse(user);
  }

  @Override
  public List<UserResponse> getAllUsers() {
    return userRepository.findAll()
      .stream()
      .map(userMapper::toResponse)
      .toList();
  }

  @Override
  @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
  @Transactional
  public void deleteUserById(Long id) {
    userRepository.delete(getUserEntityById(id));
    log.info("User with id {} deleted", id);
  }

  @Override
  @PreAuthorize("hasRole('ADMIN')")
  @Transactional
  public UserResponse changeRole(Long id, Role role) {
    User user = getUserEntityById(id);

    user.setRole(role);
    userRepository.save(user);

    log.info("User {} role changed to {}", user.getId(), role);

    return userMapper.toResponse(user);
  }

  @Override
  public List<UserResponse> getAllUsersByRole(Role role) {
    return userRepository.findAllByRole(role)
      .stream()
      .map(userMapper::toResponse)
      .toList();
  }

  private User getUserEntityById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }
}
