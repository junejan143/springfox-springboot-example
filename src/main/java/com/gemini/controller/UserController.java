package com.gemini.controller;

import com.gemini.domain.User;
import com.gemini.service.IUserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@Api(description ="用户部分信息", tags = "User", produces = "application/json", consumes = "application/json")
public class UserController {
    @Autowired
    private IUserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/user" , method = RequestMethod.POST)
    public int createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        return 1;
    }

    @RequestMapping(value = "/user/{userId}" , method = RequestMethod.GET)
    public User getUser(@Valid @PathVariable int userId){
        return userService.getUser(userId);
    }

}
