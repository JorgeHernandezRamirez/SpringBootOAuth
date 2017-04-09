package com.jorgehernandezramirez.spring.springboot.oauth.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @Autowired
    private OAuth2RestOperations oAuth2RestOperations;

    public ResourceController(){
        //Para Spring
    }

    @RequestMapping("/user")
    public String getUser() {
        return oAuth2RestOperations.getForObject("http://localhost:8080/user", String.class);
    }

    @RequestMapping("/private")
    public String getPrivateResource() {
        return oAuth2RestOperations.getForObject("http://localhost:8080/private", String.class);
    }
}
