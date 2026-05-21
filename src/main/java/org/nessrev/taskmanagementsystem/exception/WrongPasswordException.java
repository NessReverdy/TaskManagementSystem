package org.nessrev.taskmanagementsystem.exception;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String username) {
        super("Wrong password for user " + username);
    }
}
