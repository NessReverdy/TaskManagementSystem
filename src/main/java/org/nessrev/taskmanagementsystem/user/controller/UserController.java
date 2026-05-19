package org.nessrev.taskmanagementsystem.user.controller;

import org.nessrev.taskmanagementsystem.user.dto.UserRequest;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
public interface UserController {
     @PostMapping
     ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest);

     @GetMapping("/{id}")
     ResponseEntity<UserResponse> getUserById(@PathVariable Long id);

     @GetMapping
     ResponseEntity<List<UserResponse>> getAllUsers();

     @DeleteMapping("/{id}")
     ResponseEntity<Void> deleteUserById(@PathVariable Long id);

     @PatchMapping("/{id}/username")
     ResponseEntity<UserResponse> updateUsername(
             @PathVariable Long id,
             @RequestParam String newUsername
     );

     @PatchMapping("/{id}/password")
     ResponseEntity<UserResponse> updateUserPassword(
             @PathVariable Long id,
             @RequestParam String newPassword);

     @PatchMapping("/{id}/admin")
     ResponseEntity<UserResponse> changeAdminRole(@PathVariable Long id);

     @GetMapping("/admins")
     ResponseEntity<List<UserResponse>> getAllAdmins();
}
