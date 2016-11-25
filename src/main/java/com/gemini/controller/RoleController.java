package com.gemini.controller;

import com.gemini.domain.Role;
import com.gemini.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/admin")
//@Api
public class RoleController {
    @Autowired
    private IUserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(value = "/role" , method = RequestMethod.POST)
    public int createRole(@Valid @RequestBody Role role, @RequestParam(required = false) int pageNo, int pageSize){
        return 222;
    }

    //@ResponseStatus(HttpStatus.NO_CONTENT)
    @RequestMapping(value = "/role/{roleId}" , method = RequestMethod.GET)
    public int getRole(@PathVariable int roleId){
        if(roleId == 1){
            return 100;
        }else {
            return roleId;
        }
    }

}
