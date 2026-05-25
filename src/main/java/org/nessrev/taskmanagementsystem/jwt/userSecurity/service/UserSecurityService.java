package org.nessrev.taskmanagementsystem.jwt.userSecurity.service;

import lombok.RequiredArgsConstructor;
import org.nessrev.taskmanagementsystem.jwt.userSecurity.entity.UserSecurity;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.nessrev.taskmanagementsystem.user.repo.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserSecurity loadUserByUsername(String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new UserSecurity(user);
    }
}
