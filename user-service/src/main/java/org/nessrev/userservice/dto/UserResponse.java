package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.nessrev.userservice.enums.Role;

public record UserResponse(
  Long id,
  @NotBlank
  @Size(min = 2, max = 50)
  String username,
  Role role
){
}
