package org.nessrev.taskmanagementsystem.user.repo;

import org.nessrev.taskmanagementsystem.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT user FROM User user WHERE user.admin = true")
    List<User> findAllAdmins();
}
