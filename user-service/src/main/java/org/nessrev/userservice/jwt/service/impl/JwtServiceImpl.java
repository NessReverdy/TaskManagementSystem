package org.nessrev.userservice.jwt.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.nessrev.userservice.jwt.service.JwtService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

@Service
public class JwtServiceImpl implements JwtService {

  private final SecretKey key;

  public JwtServiceImpl(@Value("${jwt.secret}") String secret) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(key)
      .build()
      .parseClaimsJws(token)
      .getBody();
  }

  @Override
  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  @Override
  public String extractRole(String token) {
    return extractAllClaims(token).get("role", String.class);
  }

  @Override
  public boolean isTokenValid(String token) {
    try {
      extractAllClaims(token);
      return true;
    } catch (Exception e) {
      return false;
    }
  }
}
