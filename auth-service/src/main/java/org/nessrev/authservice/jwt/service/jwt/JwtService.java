package org.nessrev.authservice.jwt.service.jwt;

import io.jsonwebtoken.Claims;
import org.nessrev.authservice.auth.entity.AuthUser;
import org.nessrev.authservice.jwt.userSecurity.entity.UserSecurity;

public interface JwtService {
  String generateAccessToken(AuthUser user);
  String generateRefreshToken(AuthUser user);
  String extractUsername(String token);
  Claims extractAllClaims(String token);
  boolean isTokenExpired(String token);
  boolean isTokenValid(String token, UserSecurity userSecurity);
}
