package com.jorgehernandezramirez.spring.springboot.oauth.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    public LoginController(){
        //Para Spring
    }

    @RequestMapping("/loginok")
    public String doLoginOk() {
        LOGGER.info("Autentication -> {}", SecurityContextHolder.getContext().getAuthentication());
        return "loginok";
    }

    @RequestMapping("/loginko")
    public String doLoginKo() {
        return "loginko";
    }
}
