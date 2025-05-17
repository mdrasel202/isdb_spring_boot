package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountRequestDTO {
    private Integer userId;
    private AccountType type;
    private String name;
    private BigDecimal balance;
    private LocalDate requestDate;
}
