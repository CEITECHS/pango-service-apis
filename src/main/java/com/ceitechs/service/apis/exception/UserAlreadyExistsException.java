package com.ceitechs.service.apis.exception;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class UserAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UserAlreadyExistsException() {
        super();
    }

    public UserAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }

    public UserAlreadyExistsException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
