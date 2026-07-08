package org.nessrev.userservice.controller.impl;

import org.nessrev.userservice.controller.UserController;
import org.nessrev.userservice.dto.*;
import org.nessrev.userservice.enums.Role;
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

  @Override
  public ResponseEntity<UserResponse> updateUser(Long id, UserRequest request) {
    return ResponseEntity.ok(
      userService.updateUser(id, request)
    );
  }

  @Override
  public ResponseEntity<UserResponse> changeRole(Long id, ChangeRoleRequest request) {
    return ResponseEntity.ok(
      userService.changeRole(id, request.role())
    );
  }

  @Override
  public ResponseEntity<List<UserResponse>> getAllUsersByRole(Role role) {
    return ResponseEntity.ok(userService.getAllUsersByRole(role));
  }
}
