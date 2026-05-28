package org.nessrev.taskmanagementsystem.user.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.nessrev.taskmanagementsystem.projects.entity.Project;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Validated
@RequestMapping("/users")
public interface UserController {
     @GetMapping("/{id}")
     ResponseEntity<UserResponse> getUserById(
             @PathVariable
             @NotNull
             Long id);

     @GetMapping
     ResponseEntity<List<UserResponse>> getAllUsers();

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
     ResponseEntity<UserResponse> updateUserPassword(
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

     @GetMapping("/{username}/projects")
     ResponseEntity<Set<Project>> getAllProjectsByUsername(@PathVariable String username);
}
