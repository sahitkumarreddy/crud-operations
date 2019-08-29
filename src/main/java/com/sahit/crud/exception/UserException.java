package com.sahit.crud.exception;

/**
 * Created by sahit on 29-08-2019.
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
