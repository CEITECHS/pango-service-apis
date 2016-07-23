package com.ceitechs.service.apis.exception;

/**
 * 
 * @author abhisheksingh -
 * @since 1.0
 */
public class FileUploadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public FileUploadException() {
        super();
    }

    public FileUploadException(String errorMessage) {
        super(errorMessage);
    }

    public FileUploadException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
