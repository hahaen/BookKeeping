package com.hahaen.bookkeeping.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControllers {
    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
