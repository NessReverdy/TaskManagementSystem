package org.nessrev.taskmanagementsystem.exception.custom;

public class TokenHashingException extends RuntimeException {
    public TokenHashingException(Throwable cause) {
        super("Error hashing token", cause);
    }
}
