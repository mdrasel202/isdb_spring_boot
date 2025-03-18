package com.rasel.journalApp.service;

import com.rasel.journalApp.entity.User;
import com.rasel.journalApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

   //get
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //save
    public String saveEntry(User user){
        userRepository.save(user);
        return "save";
    }

    //delete
    public void delete(long id){
        userRepository.deleteById(id);
    }

    //select
    public Optional<User> findId(long id){
        return userRepository.findById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
