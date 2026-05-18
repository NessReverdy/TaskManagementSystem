package org.nessrev.taskmanagementsystem.user.service;

import lombok.AllArgsConstructor;
import org.nessrev.taskmanagementsystem.user.entity.User;
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

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    @Transactional
    public boolean createUser(User user) {
        if (userRepository.existsById(user.getId())) {
            userRepository.save(user);
            log.info("User created with id {} == UserService ==", user.getId());
            return true;
        } else {
            log.info("User is already exist == UserService ==");
            return false;
        }
    }

    public User changeName(User user, String newName) {
        user.setUsername(newName);
        return userRepository.save(user);
    }

    public User changePassword(User user, String newPassword) {
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean deleteUserById(long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            log.info("User is deleted with id {} == UserService ==", id);
            return true;
        } else {
            log.info("User isn't deleted == UserService ==");
            return false;
        }
    }

    public boolean setAdminRole(User user){
        if (!user.isAdmin()) {
            user.setAdmin(true);
            userRepository.save(user);
            log.info("User {} is set role <admin> == UserService ==", user.getId());
            return true;
        } else {
            log.info("User {} is already admin == UserService ==", user.getId());
            return false;
        }
    }

    public List<User> getAllAdmins(){
        return userRepository.findAllAdmins();
    }
}
