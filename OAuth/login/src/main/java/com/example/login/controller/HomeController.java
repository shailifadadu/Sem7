package com.example.login.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan(basePackages = {"com.example.login.config"})
@RestController
public class HomeController {

    @GetMapping("/")
    public String home(){
        return "Hello, Home!";
    }

    @GetMapping("/secured")
    public String secured(){
        return "Hello, Secured!";
    }
}
