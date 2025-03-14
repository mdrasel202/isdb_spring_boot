package com.rasel.journalApp.controller;

import com.rasel.journalApp.entity.User;
import com.rasel.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journalUsr")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    private List<User> getAlluser(){
        return userService.getAll();
    }

    @PostMapping
    private void createUsr(@RequestBody User user){
        userService.saveEntry(user);
    }

    @PutMapping
    private ResponseEntity<?> updateUser(@RequestBody User user){
       User userInDB = userService.findByUserName(user.getUserName());
       if(userInDB != null){
           userInDB.setUserName(user.getUserName());
           userInDB.setPassword(user.getPassword());
           userService.saveEntry(userInDB);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
