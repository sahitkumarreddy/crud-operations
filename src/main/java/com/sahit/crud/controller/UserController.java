package com.sahit.crud.controller;

/**
 * Controller class
 */
import com.sahit.crud.exception.UserException;
import com.sahit.crud.model.User;
import com.sahit.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping({"/user"})
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Create user
     * @param user user
     * @return user details
     * @throws UserException userException
     */
    @PostMapping("/create")
    public User createUser(@RequestBody User user) throws UserException {
        return userService.createUser(user);
    }

    /**
     * Get user details by Id
     * @param id id
     * @return user details
     * @throws UserException userException
     */
    @GetMapping(path = {"/{id}"})
    public User findUserById(@PathVariable("id") int id) throws UserException{
        return userService.findUserById(id);
    }

    /**
     * To update user details by id
     * @param id id
     * @param user user
     * @return user details
     * @throws UserException userException
     */
    @PutMapping(path ={"/update/{id}"})
    public User updateUser(@PathVariable("id") int id,@RequestBody User user) throws UserException{
        return userService.updateUser(id,user);
    }

    /**
     * To delete user by id
     * @param id
     * @return No content
     * @throws UserException userException
     */
    @DeleteMapping(path ={"/delete/{id}"})
    public User deleteUser(@PathVariable("id") int id)throws UserException {
        return userService.deleteUser(id);
    }

    /**
     * To get all the users
     * @return list of users
     * @throws UserException userException
     */
    @GetMapping(path ={"/listusers"})
    public List<User> findAllUsers() throws UserException{
        return userService.findAllUsers();
    }
}