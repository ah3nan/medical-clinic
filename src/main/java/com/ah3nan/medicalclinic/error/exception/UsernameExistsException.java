package com.ah3nan.medicalclinic.error.exception;

public class UsernameExistsException extends RuntimeException {

    public UsernameExistsException() {
        super();
    }
    public UsernameExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public UsernameExistsException(String message) {
        super(message);
    }
    public UsernameExistsException(Throwable cause) {
        super(cause);
    }
}
