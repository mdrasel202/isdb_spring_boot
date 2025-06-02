package com.rasel.bank_management.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DepostRequestDTO {
    private String accountNumber;
    private BigDecimal amount;
}
