package org.nessrev.taskmanagementsystem.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private long id;
    private String username;
    private boolean admin;

    public UserResponse(long id, String username, boolean admin) {
        this.id = id;
        this.username = username;
        this.admin = admin;
    }
}
