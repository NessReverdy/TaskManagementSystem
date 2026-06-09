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
    User user = userMapper.toEntity(request, encodedPassword);
    userRepository.save(user);

    log.info("User created with id {}", user.getId());
    return userMapper.toResponse(user);
  }

  @Override
  @Transactional
  public UserResponse updateUsername(Long id, String newUsername) {
    User user = getUserEntityById(id);
    if (user.getUsername().equals(newUsername)) {
      return userMapper.toResponse(user);
    }
    if (userRepository.existsByUsername(newUsername)) {
      log.warn("User with this name already exists");
      throw new UserAlreadyExistsException(newUsername);
    }

    user.setUsername(newUsername);
    return userMapper.toResponse(userRepository.save(user));
  }

  @Override
  @Transactional
  public UserResponse updatePassword(Long id, String newPassword) {
    User user = getUserEntityById(id);
    user.setPassword(passwordEncoder.encode(newPassword));
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
  @Transactional
  public void deleteUserById(Long id) {
    userRepository.delete(getUserEntityById(id));
    log.info("User with id {} deleted", id);
  }

  @Override
  @Transactional
  public UserResponse changeRole(Long id, Role role) { //todo переписать
    User user = getUserEntityById(id);

    if (user.getRole() == Role.ADMIN) {
      user.setRole(Role.USER);
      userRepository.save(user);
      log.info("User {} isn't admin now", user.getId());
      return userMapper.toResponse(user);
    }

    user.setRole(Role.ADMIN);
    userRepository.save(user);

    log.info("User {} promoted to admin", user.getId());
    return userMapper.toResponse(user);
  }

  @Override
  public List<UserResponse> getAllAdmins() {
    return userRepository.findByRole(Role.ADMIN)
      .stream()
      .map(userMapper::toResponse)
      .toList();
  }

  private User getUserEntityById(Long id) {
    return userRepository.findById(id)
      .orElseThrow(() -> new UserNotFoundException(id));
  }
}
