package org.nessrev.authservice.jwt.service.jwt.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.nessrev.authservice.entity.AuthUser;
import org.nessrev.authservice.jwt.service.jwt.JwtService;
import org.nessrev.authservice.jwt.userSecurity.entity.UserSecurity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
  private final String secret;

  public JwtServiceImpl(
    @Value("${jwt.secret}")
    String secret) {
    this.secret = secret;
  }

  private Key getSigningKey() {
    return Keys.hmacShaKeyFor(secret.getBytes());
  }

  @Override
  public String generateAccessToken(AuthUser user) {
    return Jwts.builder()
      .setSubject(user.getUsername())
      .claim("id", user.getId())
      .claim("role", user.getRole().name())
      .claim("username", user.getUsername())
      .setIssuedAt(new Date())
      .setExpiration(new Date(System.currentTimeMillis() + 15 * 60 * 1000)) //15 min
      .signWith(getSigningKey())
      .compact();
  }

  @Override
  public String generateRefreshToken(AuthUser user) {
    Date issuedAt = new Date();
    Date expiry = new Date(
      System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L // 7 days
    );

    Long userId = user.getId();

    return Jwts.builder()
      .setSubject(user.getUsername())
      .claim("id", userId)
      .claim("role", user.getRole().name())
      .claim("username", user.getUsername())
      .setIssuedAt(issuedAt)
      .setExpiration(expiry)
      .signWith(getSigningKey())
      .compact();
  }

  @Override
  public String extractUsername(String token) {
    return extractAllClaims(token).getSubject();
  }

  @Override
  public boolean isTokenValid(String token, UserSecurity userSecurity) {
    final String username = extractUsername(token);

    return username.equals(userSecurity.getUsername())
      && !isTokenExpired(token);
  }

  @Override
  public boolean isTokenExpired(String token) {
    return extractAllClaims(token)
      .getExpiration()
      .before(new Date());
  }

  @Override
  public Claims extractAllClaims(String token) {
    return Jwts.parserBuilder()
      .setSigningKey(getSigningKey())
      .build()
      .parseClaimsJws(token)
      .getBody();
  }
}
