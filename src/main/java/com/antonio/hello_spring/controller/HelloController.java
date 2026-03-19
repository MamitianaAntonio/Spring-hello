package com.antonio.hello_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello world";
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(@RequestParam String name) {
        if (name == null || name.isBlank()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Parameter name is required");
        } else {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("Content-type", "text/plain")
                    .body("Welcome " + name);
        }
    }
}
