package org.nessrev.userservice.controller.impl;

import org.nessrev.userservice.controller.UserController;
import org.nessrev.userservice.dto.*;
import org.nessrev.userservice.service.UserService;
import org.nessrev.userservice.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
  private final UserService userService;

  public UserControllerImpl(UserServiceImpl userServiceImpl) {
    this.userService = userServiceImpl;
  }

  @Override
  public ResponseEntity<UserResponse> createUser(UserRequest request) {
    return ResponseEntity.ok(userService.createUser(request));
  }

  @Override
  public ResponseEntity<UserResponse> getUserById(Long id) {
    return ResponseEntity.ok(userService.getUserById(id));
  }

  @Override
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @Override
  public ResponseEntity<Void> deleteUserById(Long id) {
    userService.deleteUserById(id);
    return ResponseEntity.noContent().build();
  }

  //todo юзер может менять только свои данные, админ любые
  @Override
  public ResponseEntity<UserResponse> updateUsername(Long id, UpdateUsernameRequest request) {
    return ResponseEntity.ok(
      userService.updateUsername(id, request.getUsername())
    );
  }

  @Override
  public ResponseEntity<UserResponse> updateUserPassword(Long id, UpdatePasswordRequest request) {
    return ResponseEntity.ok(
      userService.updatePassword(id, request.getPassword())
    );
  }

  @Override
  public ResponseEntity<UserResponse> changeRole(Long id, ChangeRoleRequest request) {
    return ResponseEntity.ok(
      userService.changeRole(id, request.getRole())
    );
  }

  @Override
  public ResponseEntity<List<UserResponse>> getAllAdmins() {
    return ResponseEntity.ok(userService.getAllAdmins());
  }
}
