package com.rasel.bank_management.service;

import com.rasel.bank_management.model.User;
import com.rasel.bank_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //post
    public User saveUser(User user) {
        User saves = userRepository.save(user);
        return saves;
    }

    //get
    public User getUser(Integer id) {
        User getuser = userRepository.findById(id).orElse(null);
        return getuser;
    }

    //get All
    public List<User> getAll() {
        List<User> getall = userRepository.findAll();
        return getall;
    }

    //delete id
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    //put
    public User updateUser(Integer id, User user) {
        Optional<User> userId = userRepository.findById(id);

        if(userId.isPresent()){
            User auser = new User();

            if(user.getName() != null){
                auser.setName(user.getName());
            }

            if(user.getEmail() != null){
                auser.setEmail(user.getEmail());
            }

            if(user.getPassword() != null){
                auser.setPassword(user.getPassword());
            }

            if(user.getPhone() != null){
                auser.setPhone(user.getPhone());
            }

            if(user.getAddress() != null){
                auser.setAddress(user.getAddress());
            }

            if(user.getBirthDay() != null){
                auser.setBirthDay(user.getBirthDay());
            }
            return userRepository.save(auser);
        }else{
            throw  new IllegalArgumentException("User not found");
        }
    }
}
