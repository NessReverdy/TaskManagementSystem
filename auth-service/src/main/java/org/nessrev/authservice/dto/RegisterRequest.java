package org.nessrev.authservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.nessrev.authservice.enums.Role;

public record RegisterRequest(
  @NotBlank
  @Size(min = 2, max = 50)
  String username,
  @NotBlank
  @Size(min = 4, max = 8)
  String password,
  @NotNull
  Role role
){}
