package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.LoanStatus;
import com.rasel.bank_management.model.Loan;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class LoanResponseDTO {
    private Long loanId;
    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private BigDecimal availableAmount;
    private LocalDate dueDate;
    private LocalDate acceptDate;
    private LoanStatus status;
    private LocalDate applicationDate;

    private BigDecimal monthlyInterest;
    private BigDecimal yearlyInterest;

}
