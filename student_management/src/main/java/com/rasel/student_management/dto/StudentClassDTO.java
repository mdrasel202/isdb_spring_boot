package com.rasel.student_management.dto;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentClassDTO {
    @Nonnull
    @Size(min = 3, max = 30, message = "Thik thak name de")
    private String name;
    @Nonnull
    private Integer classTeacherId;
    @Nonnull
    private Integer roomNumber;
}
