package org.nessrev.taskmanagementsystem.user.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false, length = 50)
    private String username;
    @Column(unique = true, nullable = false, length = 8)
    private String password;
    @Column(nullable = false)
    private boolean admin = false;
}
