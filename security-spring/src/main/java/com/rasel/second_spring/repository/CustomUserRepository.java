package com.rasel.second_spring.repository;


import com.rasel.second_spring.model.CustomUser;

public interface CustomUserRepository {
    CustomUser findCustomUserByEmail(String email);
}