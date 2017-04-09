package com.jorgehernandezramirez.spring.springboot.oauth.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    public UserController(){
        //Para Spring
    }

    @RequestMapping("/user")
    public Principal getPublicResource(final Principal principal){
        return principal;
    }
}
