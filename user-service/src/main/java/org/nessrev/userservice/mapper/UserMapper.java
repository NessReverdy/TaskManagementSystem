package org.nessrev.userservice.mapper;

import org.nessrev.userservice.dto.UserRequest;
import org.nessrev.userservice.dto.UserResponse;
import org.nessrev.userservice.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse toResponse(User user) {
    return new UserResponse(
      user.getId(),
      user.getUsername(),
      user.getRole()
    );
  }

  public User toEntity(UserRequest userRequest, String encodedPassword) {
    User user = new User();
    user.setUsername(userRequest.getUsername());
    user.setPassword(encodedPassword);
    user.setRole(userRequest.getRole());
    return user;
  }
}
