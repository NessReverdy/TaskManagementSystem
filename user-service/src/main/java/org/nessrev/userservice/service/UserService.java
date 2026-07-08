package org.nessrev.userservice.service;

import org.nessrev.userservice.dto.UserCreatedEvent;
import org.nessrev.userservice.dto.UserRequest;
import org.nessrev.userservice.dto.UserResponse;
import org.nessrev.userservice.enums.Role;

import java.util.List;

public interface UserService {
  void createUser(UserCreatedEvent userCreatedEvent);
  UserResponse updateUser(Long id, UserRequest request);
  UserResponse getUserById(Long id);
  List<UserResponse> getAllUsers();
  void deleteUserById(Long id);
  UserResponse changeRole(Long id, Role role);
  List<UserResponse> getAllUsersByRole(Role role);
}
