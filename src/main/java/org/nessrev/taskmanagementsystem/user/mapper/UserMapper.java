package org.nessrev.taskmanagementsystem.user.mapper;

import org.nessrev.taskmanagementsystem.user.dto.UserFullInfo;
import org.nessrev.taskmanagementsystem.user.dto.UserRequest;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.isAdmin()
        );
    }

    public User toEntity(UserRequest userRequest) {
        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setAdmin(userRequest.isAdmin());
        return user;
    }

    public void updateRequest(User user, UserRequest userRequest) {
        user.setUsername(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setAdmin(userRequest.isAdmin());
    }

    public UserFullInfo toFullInfo(User user) {
        return new UserFullInfo(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.isAdmin()
        );
    }
}
