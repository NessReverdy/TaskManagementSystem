package org.nessrev.taskmanagementsystem.jwt.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.nessrev.taskmanagementsystem.user.entity.User;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "refresh_tokens")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, unique = true, length = 255)
    private String tokenHash;

    @Column(nullable = false)
    private Date expiresAt;

    @Column(nullable = false, updatable = false)
    private Date issuedAt;

    @Column(nullable = false)
    private boolean revoked = false;

}
