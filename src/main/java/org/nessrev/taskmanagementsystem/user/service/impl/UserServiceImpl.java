package org.nessrev.taskmanagementsystem.user.service;

import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.exception.custom.UserAlreadyExistsException;
import org.nessrev.taskmanagementsystem.exception.custom.UserNotFoundException;
import org.nessrev.taskmanagementsystem.projects.entity.Project;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.nessrev.taskmanagementsystem.user.enums.Role;
import org.nessrev.taskmanagementsystem.user.mapper.UserMapper;
import org.nessrev.taskmanagementsystem.user.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {
  private static final Logger log = LoggerFactory.getLogger(UserService.class);
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Transactional
  public UserResponse changeName(Long id, String newName) {
    User user = getUserEntityById(id);
    if (user.getUsername().equals(newName)) {
      return userMapper.toResponse(user);
    }

    if (userRepository.existsByUsername(newName)) {
      log.warn("User with this name already exists");
      throw new UserAlreadyExistsException(newName);
    }

    user.setUsername(newName);
    return userMapper.toResponse(userRepository.save(user));
  }

  @Transactional
  public UserResponse changePassword(Long id, String newPassword) {
    User user = getUserEntityById(id);
    user.setPassword(newPassword);
    return userMapper.toResponse(userRepository.save(user));
  }

  public UserResponse getUserById(Long id) {
    User user = getUserEntityById(id);
    return userMapper.toResponse(user);
  }

  public List<UserResponse> getAllUsers() {
    return userRepository.findAll()
      .stream()
      .map(userMapper::toResponse)
      .toList();
  }

  @Transactional
  public void deleteUserById(Long id) {
    userRepository.delete(getUserEntityById(id));
    log.info("User with id {} deleted", id);
  }

  @Transactional
  public UserResponse changeAdminRole(Long id) {
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

  @Transactional(readOnly = true)
  public Set<Project> getUserProjectsByUsername(String username) {
    User user = userRepository.findByUsername(username)
      .orElseThrow(() -> new UserNotFoundException(username));

    return user.getProjects();
  }
}
