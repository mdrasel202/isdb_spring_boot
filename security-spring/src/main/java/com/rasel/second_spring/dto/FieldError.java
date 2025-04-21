package com.rasel.second_spring.dto;

public record FieldError(
        String field,
        String errorCode,
        String errorMessage
) {
}