package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.BankDepositStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BankDepositResponseDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal depositAmount;
    private BigDecimal interestEarned;

    private double interestRate;
    private String interestRateLabel;

    private BankDepositStatus bankDepositStatus;
    private LocalDate startDate;
    private LocalDate maturityDatel;

}
