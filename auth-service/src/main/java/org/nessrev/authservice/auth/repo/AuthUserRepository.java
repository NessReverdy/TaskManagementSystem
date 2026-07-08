package org.nessrev.authservice.auth.repo;

import org.nessrev.authservice.auth.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
  boolean existsByUsername(String username);

  Optional<AuthUser> findByUsername(String username);
}
