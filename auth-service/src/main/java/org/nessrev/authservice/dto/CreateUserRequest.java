package org.nessrev.authservice.dto;

import org.nessrev.authservice.enums.Role;

public record CreateUserRequest(
  Long id,
  String username,
  Role role
) {
}