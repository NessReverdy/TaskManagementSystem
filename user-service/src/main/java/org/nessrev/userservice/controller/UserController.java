package org.nessrev.userservice.controller;

import org.nessrev.userservice.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/users")
public interface UserController {
  @PostMapping
  ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request);

  @GetMapping("/{id}")
  ResponseEntity<UserResponse> getUserById(@PathVariable Long id);

  @GetMapping
  ResponseEntity<List<UserResponse>> getAllUsers();

  @DeleteMapping("/admin/{id}")
  ResponseEntity<Void> deleteUserById(@PathVariable Long id);

  @PatchMapping("/{id}/username")
  ResponseEntity<UserResponse> updateUsername(
    @PathVariable Long id,
    @RequestBody UpdateUsernameRequest request
  );

  @PatchMapping("/{id}/password")
  ResponseEntity<UserResponse> updateUserPassword(
    @PathVariable Long id,
    @RequestBody UpdatePasswordRequest request);

  @PatchMapping("/admin/{id}/role")
  ResponseEntity<UserResponse> changeRole(
    @PathVariable Long id,
    @RequestBody ChangeRoleRequest request
  );

  @GetMapping("/admin/admins")
  ResponseEntity<List<UserResponse>> getAllAdmins();
}
