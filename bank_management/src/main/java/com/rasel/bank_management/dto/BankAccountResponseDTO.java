package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.AccountStatus;
import com.rasel.bank_management.constants.AccountType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BankAccountResponseDTO {
    private Long id;
    private String accountNumber;
    private Integer userId;
    private String userName;
    private AccountType type;
    private AccountStatus status;
    private BigDecimal availableBalance;
    private LocalDate openedDate;
}
