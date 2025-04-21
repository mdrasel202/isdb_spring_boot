package com.rasel.second_spring.dto;

import java.util.List;

public record ErrorResponse(
        Integer httpStatus,
        String exception,
        String message,
        List<FieldError> fieldErrors
) {
}