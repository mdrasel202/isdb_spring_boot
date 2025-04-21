package com.rasel.second_spring.dto;

import com.rasel.second_spring.constants.Role;
import jakarta.validation.constraints.Email;

public record UserUpdateRequest(
        @Email(message = "Email should be valid")
        String email,

        Role role,
        String firstName,
        String lastName,
        String phoneNumber
) {
}