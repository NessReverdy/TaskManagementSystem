package org.nessrev.authservice.jwt.userSecurity.service;

import lombok.RequiredArgsConstructor;
import org.nessrev.authservice.auth.entity.AuthUser;
import org.nessrev.authservice.auth.repo.AuthUserRepository;
import org.nessrev.authservice.jwt.userSecurity.entity.UserSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

  private final AuthUserRepository authUserRepository;

  @Override
  public UserSecurity loadUserByUsername(String username)
    throws UsernameNotFoundException {

    AuthUser user = authUserRepository.findByUsername(username)
      .orElseThrow(() ->
        new UsernameNotFoundException("User not found"));

    return new UserSecurity(user);
  }
}
