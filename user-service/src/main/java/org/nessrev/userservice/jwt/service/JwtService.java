package org.nessrev.userservice.jwt.service;

public interface JwtService {
  String extractUsername(String token);
  String extractRole(String token);
  boolean isTokenValid(String token);
}
