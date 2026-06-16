package org.nessrev.authservice.exception.custom;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String username) {
    super("User already exists: " + username);
  }
}
