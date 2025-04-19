package com.example.second_project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GreentingsController {

    @GetMapping("/greetings")
    public ResponseEntity<String> sayHello(){
        return ResponseEntity.ok("Hello from our api");
    }

    @GetMapping("/say-good-bye")
    public ResponseEntity<String> sayGoodBye(){
        return ResponseEntity.ok("Good by and see you later");
    }
}
