package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.DepositStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class DepositResponseDTO {
    private Long id;
    private BigDecimal amount;
    private String accountNumber;
    private LocalDate date;
    private DepositStatus status;
}
