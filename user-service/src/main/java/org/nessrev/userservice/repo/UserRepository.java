package org.nessrev.userservice.repo;

import org.nessrev.userservice.entity.User;
import org.nessrev.userservice.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByUsername(String username);

  @Query("SELECT u FROM User u WHERE u.role = :role")
  List<User> findByRole(@Param("role") Role role);

  @Query("SELECT u FROM User u WHERE u.username = :username")
  Optional<User> findByUsername(@Param("username") String username);
}
