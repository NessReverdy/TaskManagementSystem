package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.nessrev.userservice.enums.Role;

public record UserRequest(
  @NotBlank
  @Size(min = 2, max = 50)
  String username,
  Role role
){}
