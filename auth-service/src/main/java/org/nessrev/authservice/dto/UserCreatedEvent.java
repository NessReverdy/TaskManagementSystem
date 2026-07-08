package org.nessrev.authservice.dto;

import org.nessrev.authservice.enums.Role;

public record UserCreatedEvent(
  Long id,
  String username,
  Role role
) {
}