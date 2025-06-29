package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.BankDepositStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class BankDepositRequestDTO {
    private String accountNumber;
    private BigDecimal depositAmount;
    private BankDepositStatus bankDepositStatus;
}
