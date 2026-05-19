package org.nessrev.taskmanagementsystem.user.controller;

import org.nessrev.taskmanagementsystem.user.dto.UserRequest;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.nessrev.taskmanagementsystem.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserControllerImpl implements UserController {
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest) {
        return ResponseEntity.ok(userService.createUser(userRequest));
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
    public ResponseEntity<UserResponse> updateUsername(Long id, String newUsername) {
        return ResponseEntity.ok(userService.changeName(id, newUsername));
    }

    @Override
    public ResponseEntity<UserResponse> updateUserPassword(Long id, String newPassword) {
        return ResponseEntity.ok(userService.changePassword(id, newPassword));
    }

    @Override
    public ResponseEntity<UserResponse> changeAdminRole(Long id) {
        return ResponseEntity.ok(userService.changeAdminRole(id));
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllAdmins() {
        return ResponseEntity.ok(userService.getAllAdmins());
    }
}
