package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.userservice.enums.Role;

@Getter
@Setter
public class ChangeRoleRequest {
  @NotNull
  private Role role;
}
