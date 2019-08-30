package com.sahit.crud.advice;

import com.sahit.crud.exception.UserException;
import com.sahit.crud.util.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class to handle the exceptions
 */
@ControllerAdvice
public class UserAdvice {


    @ExceptionHandler(UserException.class)
    public ResponseEntity<String> userExceptionHandler(UserException e) {
        if(e.getErrorCode().equals(AppConstants.VALIDATION_ERROR)) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }else if(e.getErrorCode().equals(AppConstants.NOT_FOUND)){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

