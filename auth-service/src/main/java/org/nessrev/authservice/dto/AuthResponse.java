package org.nessrev.authservice.dto;

public record AuthResponse(
  String accessToken,
  String refreshToken
) {
}
