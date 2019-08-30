package com.sahit.crud.exception;

/**
 * Class to handle the exceptions
 */
public class UserException extends Exception {

    private final String errorCode;

    public UserException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
