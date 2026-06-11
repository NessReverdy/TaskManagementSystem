package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotNull;
import org.nessrev.userservice.enums.Role;

public record ChangeRoleRequest(@NotNull Role role) {}
