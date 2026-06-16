package org.nessrev.authservice.jwt.service.refresh.impl;

import lombok.RequiredArgsConstructor;
import org.nessrev.authservice.exception.custom.InvalidRefreshTokenException;
import org.nessrev.authservice.jwt.entity.RefreshToken;
import org.nessrev.authservice.jwt.repo.RefreshTokenRepository;
import org.nessrev.authservice.jwt.service.hash.TokenHashService;
import org.nessrev.authservice.jwt.service.refresh.RefreshTokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {
  private final RefreshTokenRepository repository;
  private final TokenHashService hashService;

  @Override
  public void save(String refreshToken, Long userId) {
    RefreshToken token = new RefreshToken();
    token.setTokenHash(
      hashService.hash(refreshToken)
    );
    token.setUserId(userId);
    repository.save(token);
  }

  @Override
  public RefreshToken find(String token) {
    String tokenHash = hashService.hash(token);
    return repository.findById(tokenHash)
      .orElseThrow();
  }

  @Override
  public void delete(String token) {
    String tokenHash = hashService.hash(token);

    if (!repository.existsById(tokenHash)) {
      throw new InvalidRefreshTokenException();
    }

    repository.deleteById(tokenHash);
  }

  @Override
  public void deleteAllByUserId(Long userId) {
    repository.findAllByUserId(userId)
      .forEach(token ->
        repository.deleteById(token.getTokenHash())
      );
  }
}
