package com.rasel.bank_management.controller;

import com.rasel.bank_management.model.User;
import com.rasel.bank_management.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService){
        this.userService = userService;
    }

    //post
    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user){
        User saved = userService.saveUser(user);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    //get
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id){
        User user = userService.getUser(id); 
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //get all
    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
               userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //put
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        User update = userService.updateUser(id, user);
        return new ResponseEntity<>(update, HttpStatus.OK);
    }
}
