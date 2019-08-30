package com.sahit.crud.util;

import com.sahit.crud.exception.UserException;
import com.sahit.crud.model.User;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for checking validations
 */
public class Validation {

    public static void validate(User user) throws UserException {
        List<String> propertyList = new ArrayList<>();

        if (StringUtils.isEmpty(user.getFirstName())) {
            propertyList.add(AppConstants.FIRST_NAME);
        }

        if (StringUtils.isEmpty(user.getLastName())) {
            propertyList.add(AppConstants.LAST_NAME);
        }

        if (StringUtils.isEmpty(user.getEmail())) {
            propertyList.add(AppConstants.EMAIL);
        }

        if (!StringUtils.isEmpty(user.getEmail())) {
            String email=user.getEmail();
            String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
            if(!email.matches(regex)){
                throw new UserException(AppConstants.VALIDATION_ERROR,AppConstants.INVALID_EMAIL);
            }
        }

        if (!propertyList.isEmpty()) {
            throw new UserException(AppConstants.VALIDATION_ERROR,AppConstants.createErrorMsg(propertyList));
        }

    }
}
