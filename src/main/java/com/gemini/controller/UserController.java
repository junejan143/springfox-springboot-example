package com.gemini.controller;

import com.gemini.domain.User;
import com.gemini.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public int createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        return 1;
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/user/{userId}" , method = RequestMethod.GET)
    public User getUser(@Valid @PathVariable(value = "userId") int userId){
        return userService.getUser(userId);
    }

}
