package org.nessrev.taskmanagementsystem.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.taskmanagementsystem.user.enums.Role;

@Getter
@Setter
public class UserRequest {
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;

    @NotBlank
    @Size(min = 4, max = 8)
    private String password;
    private Role role;

    public UserRequest(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
