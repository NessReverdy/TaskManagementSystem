package org.nessrev.taskmanagementsystem.user.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.nessrev.taskmanagementsystem.user.dto.UserFullInfo;
import org.nessrev.taskmanagementsystem.user.dto.UserRequest;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/users")
public interface UserController {
     @PostMapping
     ResponseEntity<UserFullInfo> createUser(@RequestBody @Valid UserRequest userRequest);

     @GetMapping("/{id}")
     ResponseEntity<UserFullInfo> getUserById(
             @PathVariable
             @NotNull
             Long id);

     @GetMapping
     ResponseEntity<List<UserFullInfo>> getAllUsers();

     @DeleteMapping("/{id}")
     ResponseEntity<Void> deleteUserById(@PathVariable Long id);

     @PatchMapping("/{id}/username")
     ResponseEntity<UserResponse> updateUsername(
             @PathVariable
             @NotNull
             Long id,
             @RequestParam
             @NotBlank
             @Size(min = 2, max = 50)
             String newUsername
     );

     @PatchMapping("/{id}/password")
     ResponseEntity<UserFullInfo> updateUserPassword(
             @PathVariable
             @NotNull
             Long id,
             @RequestParam
             @Size(min = 4, max = 8)
             String newPassword);

     @PatchMapping("/{id}/admin")
     ResponseEntity<UserResponse> changeAdminRole(@PathVariable Long id);

     @GetMapping("/admins")
     ResponseEntity<List<UserResponse>> getAllAdmins();
}
