package org.nessrev.taskmanagementsystem.user.service;

import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.exception.UserAlreadyExistsException;
import org.nessrev.taskmanagementsystem.exception.UserNotFoundException;
import org.nessrev.taskmanagementsystem.user.dto.UserRequest;
import org.nessrev.taskmanagementsystem.user.dto.UserResponse;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.nessrev.taskmanagementsystem.user.mapper.UserMapper;
import org.nessrev.taskmanagementsystem.user.repo.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            log.info("User is already exist");
            throw new UserAlreadyExistsException(request.getUsername());
        }
        User user = userMapper.toEntity(request);
        userRepository.save(user);

        log.info("User created with id {}", user.getId());
        return userMapper.toResponse(user);
    }

    public UserResponse changeName(Long id, String newName) {
        User user = getUserEntityById(id);
        if (userRepository.existsByUsername(newName)
                && !user.getUsername().equals(newName)){
            log.info("User with this name is already exist");
            throw new UserAlreadyExistsException(newName);
        }
        user.setUsername(newName);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse changePassword(Long id, String newPassword) {
        User user = getUserEntityById(id);
        user.setPassword(newPassword);
        return userMapper.toResponse(userRepository.save(user));
    }

    public UserResponse getUserById(Long id) {
        User user = getUserEntityById(id);
        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    @Transactional
    public void deleteUserById(Long id) {
        userRepository.delete(getUserEntityById(id));
    }

    public UserResponse changeAdminRole(Long id) {
        User user = getUserEntityById(id);

        if (user.isAdmin()) {
            user.setAdmin(false);
            userRepository.save(user);
            log.info("User {} isn't admin now", user.getId());
            return userMapper.toResponse(user);
        }

        user.setAdmin(true);
        userRepository.save(user);

        log.info("User {} promoted to admin", user.getId());
        return userMapper.toResponse(user);
    }

    public List<UserResponse> getAllAdmins(){
        return userRepository.findAllAdmins()
                .stream()
                .map(userMapper::toResponse)
                .toList();
    }

    private User getUserEntityById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
