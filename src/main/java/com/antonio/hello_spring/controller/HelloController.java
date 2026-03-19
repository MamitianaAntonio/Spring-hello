package com.antonio.hello_spring.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public String helloWorld () {
        return "Hello world";
    }

    @GetMapping("/welcome")
    public String welcome (@RequestParam String name) {
        return "Welcome " + name;
    }
}
