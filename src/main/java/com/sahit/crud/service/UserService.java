package com.sahit.crud.service;

/**
 * Created by sahit on 29-08-2019.
 */
import com.sahit.crud.exception.UserException;
import com.sahit.crud.model.User;

import java.util.List;

public interface UserService {

    User createUser(User user) throws UserException;

    User findUserById(int id) throws UserException;

    User updateUser(int id,User user) throws UserException;

    User deleteUser(int id) throws UserException;

    List<User> findAllUsers() throws UserException;
}
