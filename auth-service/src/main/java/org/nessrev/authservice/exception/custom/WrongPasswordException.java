package org.nessrev.authservice.exception.custom;

public class WrongPasswordException extends RuntimeException {
  public WrongPasswordException(String username) {
    super("Wrong password. User:" + username);
  }
}
