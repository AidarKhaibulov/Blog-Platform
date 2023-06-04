package com.ms.exceptions;

public class EmailOrUsernameAlreadyExistsException extends RuntimeException {
    public EmailOrUsernameAlreadyExistsException(String message) {
        super(message);
    }
}
