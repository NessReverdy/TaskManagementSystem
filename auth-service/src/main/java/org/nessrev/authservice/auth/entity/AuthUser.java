package org.nessrev.authservice.auth.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.authservice.enums.Role;

@Entity
@Getter
@Setter
@Table(name = "auth_users")
public class AuthUser {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Column(unique = true, nullable = false, length = 50)
  private String username;

  @NotBlank
  @Column(nullable = false)
  private String passwordHash;

  @NotNull
  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private Role role;
}
