package org.nessrev.taskmanagementsystem.jwt.repo;

import org.nessrev.taskmanagementsystem.jwt.entity.RefreshToken;
import org.nessrev.taskmanagementsystem.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    @Transactional
    void deleteAllByUser(User user);
}
