package com.jorgehernandezramirez.spring.springboot.oauth.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    public ResourceController(){
        //Para Spring
    }

    @RequestMapping("/public")
    public String getPublicResource(){
        return "Public Resource";
    }

    @RequestMapping("/private")
    public String getResource(){
        return "Private Resource";
    }

    @RequestMapping("/admin")
    public String getAdminResource(){
        return "Admin resource";
    }

    @RequestMapping("/admin/oauth")
    public String getAdminOAuth(){
        return "Admin OAuth resource";
    }
}
