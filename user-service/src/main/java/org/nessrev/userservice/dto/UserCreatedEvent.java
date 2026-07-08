package org.nessrev.userservice.dto;

import org.nessrev.userservice.enums.Role;

public record UserCreatedEvent(
  Long id,
  String username,
  Role role
) {
}