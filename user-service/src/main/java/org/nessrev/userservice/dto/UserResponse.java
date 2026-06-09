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
public class UserResponse {
  private Long id;
  @NotBlank
  @Size(min = 2, max = 50)
  private String username;
  private Role role;
}
