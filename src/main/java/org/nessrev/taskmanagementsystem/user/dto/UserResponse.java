package org.nessrev.taskmanagementsystem.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long id;
    @NotBlank
    @Size(min = 2, max = 50)
    private String username;
    private boolean admin;

    public UserResponse(long id, String username, boolean admin) {
        this.id = id;
        this.username = username;
        this.admin = admin;
    }
}
