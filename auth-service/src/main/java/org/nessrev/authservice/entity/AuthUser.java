package org.nessrev.authservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.authservice.enums.Role;

@Entity
@Getter
@Setter
@Table(name = "auth_users")
public class AuthUser {
  @Id
  private Long id;

  @NotBlank
  @Column(unique = true, nullable = false, length = 50)
  private String username;

  @NotBlank
  @Column(nullable = false)
  private String passwordHash;

  @NotBlank
  @Column(nullable = false)
  private Role role;
}
