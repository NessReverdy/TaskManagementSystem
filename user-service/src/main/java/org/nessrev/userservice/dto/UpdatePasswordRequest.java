package org.nessrev.userservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePasswordRequest {
  @NotBlank
  @Size(min = 4, max = 8)
  private String password;
}
