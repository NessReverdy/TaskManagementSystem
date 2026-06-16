package org.nessrev.authservice.jwt.repo;

import org.nessrev.authservice.jwt.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
  List<RefreshToken> findAllByUserId(Long userId);
}
