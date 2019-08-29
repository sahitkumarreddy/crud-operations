package com.sahit.crud.controller;

/**
 * Created by sahit on 29-08-2019.
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

    @PostMapping("/create")
    public User createUser(@RequestBody User user) throws UserException {
        return userService.createUser(user);
    }

    @GetMapping(path = {"/{id}"})
    public User findUserById(@PathVariable("id") int id) throws UserException{
        return userService.findUserById(id);
    }

    @PutMapping(path ={"/update/{id}"})
    public User updateUser(@PathVariable("id") int id,@RequestBody User user) throws UserException{
        return userService.updateUser(id,user);
    }

    @DeleteMapping(path ={"/delete/{id}"})
    public User deleteUser(@PathVariable("id") int id)throws UserException {
        return userService.deleteUser(id);
    }

    @GetMapping(path ={"/listusers"})
    public List<User> findAllUsers() throws UserException{
        return userService.findAllUsers();
    }
}