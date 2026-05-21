package org.nessrev.taskmanagementsystem.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.taskmanagementsystem.user.enums.Role;

@Getter
@Setter
public class UserResponse {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;
    private Role role;

    public UserResponse(long id, String username, Role role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }
}
