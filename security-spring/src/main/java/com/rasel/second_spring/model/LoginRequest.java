package com.rasel.second_spring.model;

public record LoginRequest(
        String username,
        String password
) {
}