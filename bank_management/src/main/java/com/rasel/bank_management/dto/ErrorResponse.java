package com.rasel.bank_management.dto;

import org.springframework.validation.FieldError;

import java.util.List;

public record ErrorResponse(
        Integer httpStatus,
        String exception,
        String message,
        List<FieldError> fieldErrors
) {
}