package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.nessrev.userservice.enums.Role;

public record UserRequest(
  @NotBlank
  @Size(min = 2, max = 50)
  String username,
  @NotBlank
  @Size(min = 4, max = 8)
  String password,
  Role role
){}
