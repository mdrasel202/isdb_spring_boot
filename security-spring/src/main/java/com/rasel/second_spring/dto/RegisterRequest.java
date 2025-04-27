package com.rasel.second_spring.dto;

import com.rasel.second_spring.constants.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 5, message = "Password must be at least 5 characters")
        String password,
        Role role,
        String firstName,
        String lastName,
        String phoneNumber
) {
}