package org.nessrev.userservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.userservice.enums.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
  @Id
  private Long id;

  @NotBlank
  @Column(unique = true, nullable = false, length = 50)
  private String username;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;
}
