package com.rasel.bank_management.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.rasel.bank_management.constants.BankDepositInterestRate;
import com.rasel.bank_management.constants.BankDepositStatus;
import com.rasel.bank_management.utils.BankDepositInterestRateDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class BankDepositRequestDTO {
    private String accountNumber;
    private BigDecimal depositAmount;

    @JsonDeserialize(using = BankDepositInterestRateDeserializer.class)
    private BankDepositInterestRate bankDepositInterestRate;
    private BankDepositStatus bankDepositStatus;
}
