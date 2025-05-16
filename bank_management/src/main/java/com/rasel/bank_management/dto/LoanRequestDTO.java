package com.rasel.bank_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LoanRequestDTO {
    private Integer userId;
    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private LocalDate dueDate;
}
