package org.nessrev.userservice.exception.custom;

public class UserAlreadyExistsException extends RuntimeException {
  public UserAlreadyExistsException(String username) {
    super("User already exists: " + username);
  }
}
