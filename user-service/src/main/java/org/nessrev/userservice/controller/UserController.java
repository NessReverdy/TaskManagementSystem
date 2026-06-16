package org.nessrev.userservice.controller;

import jakarta.validation.Valid;
import org.nessrev.userservice.dto.ChangeRoleRequest;
import org.nessrev.userservice.dto.UserRequest;
import org.nessrev.userservice.dto.UserResponse;
import org.nessrev.userservice.enums.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/users")
public interface UserController {
  @PostMapping
  ResponseEntity<UserResponse> createUser(
    @RequestBody
    @Valid
    UserRequest request
  );

  @GetMapping("/{id}")
  ResponseEntity<UserResponse> getUserById(@PathVariable Long id);

  @GetMapping
  ResponseEntity<List<UserResponse>> getAllUsers();

  @DeleteMapping("/admin/{id}")
  ResponseEntity<Void> deleteUserById(@PathVariable Long id);

  @PatchMapping("/{id}/update")
  ResponseEntity<UserResponse> updateUser(
    @PathVariable Long id,
    @RequestBody
    @Valid
    UserRequest request
  );

  @PatchMapping("/admin/{id}/role")
  ResponseEntity<UserResponse> changeRole(
    @PathVariable Long id,
    @RequestBody
    @Valid
    ChangeRoleRequest request
  );

  @GetMapping("/all")
  ResponseEntity<List<UserResponse>> getAllUsersByRole(
    @RequestBody Role role
  );
}
