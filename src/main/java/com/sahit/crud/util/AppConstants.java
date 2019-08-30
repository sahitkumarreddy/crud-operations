package com.sahit.crud.util;

import java.util.List;

/**
 * Class to store constants in the application
 */
public class AppConstants {

        public static final String VALIDATION_ERROR = "Validation Error";
        public static final String FIRST_NAME = "First Name";
        public static final String LAST_NAME = "Last Name";
        public static final String EMAIL = "Email";
        public static final String INVALID_EMAIL = "Invalid Email";
        public static final String DATA_NOT_FOUND = "Data not found for given id";
        public static final String NOT_FOUND = "Data not found";
        public static final String NO_USERS = "No Users are available";

        public static final String createErrorMsg(List<String> propertyList) {
            String result = String.join(", ", propertyList);
            return result + ((propertyList.size() > 1) ? " are " : " is ") + "required";
        }

}
