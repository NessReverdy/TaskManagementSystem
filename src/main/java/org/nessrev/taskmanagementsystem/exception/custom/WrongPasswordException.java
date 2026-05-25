package org.nessrev.taskmanagementsystem.exception.custom;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String username) {
        super("Wrong password for user " + username);
    }
}
