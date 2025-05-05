package com.rasel.bank_management.dto;


import com.rasel.bank_management.constants.Role;
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