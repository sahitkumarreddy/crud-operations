package com.sahit.crud.service;

/**
 * User service Implementation
 */
import com.sahit.crud.exception.UserException;
import com.sahit.crud.model.User;
import com.sahit.crud.repository.UserRepository;
import com.sahit.crud.util.AppConstants;
import com.sahit.crud.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User createUser(User user) throws UserException {
        Validation.validate(user);
        return userRepository.save(user);
    }

    @Override
    public User findUserById(int id) throws UserException {
        User user = userRepository.findById(id);
        if(user==null){
            throw new UserException(AppConstants.NOT_FOUND,AppConstants.DATA_NOT_FOUND);
        }
        return user;
    }

    @Override
    public User updateUser(int id,User user) throws UserException{
        Validation.validate(user);
        user.setId(id);
        return userRepository.save(user);
    }

    @Override
    public User deleteUser(int id) throws UserException {
        User user = findUserById(id);
        if(user != null){
            userRepository.delete(user);
        }
        return user;
    }

    @Override
    public List<User> findAllUsers() throws UserException {
        List<User> userList=userRepository.findAll();
        if(userList==null){
            throw new UserException(AppConstants.NOT_FOUND,AppConstants.NO_USERS);
        }
        return userRepository.findAll();
    }
}
