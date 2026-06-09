package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.userservice.enums.Role;

@Getter
@Setter
@AllArgsConstructor
public class UserRequest {
  @NotBlank
  @Size(min = 2, max = 50)
  private String username;

  @NotBlank
  @Size(min = 4, max = 8)
  private String password;
  private Role role;
}
