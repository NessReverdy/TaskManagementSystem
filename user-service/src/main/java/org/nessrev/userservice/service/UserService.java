package org.nessrev.userservice.service;

import org.nessrev.userservice.dto.UserRequest;
import org.nessrev.userservice.dto.UserResponse;
import org.nessrev.userservice.enums.Role;

import java.util.List;

public interface UserService {
  UserResponse createUser(UserRequest userRequest);
  UserResponse updateUsername(Long id, String newName);
  UserResponse updatePassword(Long id, String newPassword);
  UserResponse getUserById(Long id);
  List<UserResponse> getAllUsers();
  void deleteUserById(Long id);
  UserResponse changeRole(Long id, Role role);
  List<UserResponse> getAllAdmins();
}
