package com.rasel.bank_management.dto;

public record FieldError(
        String field,
        String errorCode,
        String errorMessage
) {
}