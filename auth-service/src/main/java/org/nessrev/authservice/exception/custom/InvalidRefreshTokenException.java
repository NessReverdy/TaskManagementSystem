package org.nessrev.authservice.exception.custom;

public class InvalidRefreshTokenException extends RuntimeException {
  public InvalidRefreshTokenException() {
    super("Invalid refresh token");
  }
}
