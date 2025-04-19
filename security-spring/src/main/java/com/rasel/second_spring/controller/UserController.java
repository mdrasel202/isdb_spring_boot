package com.rasel.second_spring.controller;

import com.rasel.second_spring.annotation.CurrentUser;
import com.rasel.second_spring.model.CustomUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public CustomUser user(@CurrentUser CustomUser currentUser) {
        return currentUser;
    }
}