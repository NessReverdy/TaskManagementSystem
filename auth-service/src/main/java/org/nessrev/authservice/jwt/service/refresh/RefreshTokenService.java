package org.nessrev.authservice.jwt.service.refresh;

import org.nessrev.authservice.jwt.entity.RefreshToken;

public interface RefreshTokenService {
  void save(String refreshToken, Long userId);
  RefreshToken find(String tokenHash);
  void delete(String tokenHash);
  void deleteAllByUserId(Long userId);
}
