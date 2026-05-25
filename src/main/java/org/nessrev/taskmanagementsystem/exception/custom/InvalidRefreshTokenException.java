package org.nessrev.taskmanagementsystem.exception.custom;

public class InvalidRefreshTokenException extends RuntimeException {
    public InvalidRefreshTokenException() {
        super("Invalid refresh token");
    }
}
