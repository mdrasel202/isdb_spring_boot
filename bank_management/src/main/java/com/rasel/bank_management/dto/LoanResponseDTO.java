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
//    private Integer userId;
//    private String accountNumber;
    private BigDecimal amount;
    private BigDecimal approvedAmount;
    private BigDecimal interestRate;
    private BigDecimal availableAmount;
    private LocalDate dueDate;
    private LocalDate acceptDate;
    private LoanStatus status;
    private LocalDate applicationDate;

    private BigDecimal monthlyInterest;
    private BigDecimal yearLyInterest;



//    public LoanResponseDTO(Loan saveLoan) {
//        this.loanId = saveLoan.getId();
//        this.userId = saveLoan.getUser().getId();
//        this.accountNumber = saveLoan.getBankAccount().getAccountNumber();
//        this.amount = saveLoan.getAmount();
//        this.approvedAmount = saveLoan.getApprovedAmount();
//        this.interestRate = saveLoan.getInterestRate();
//        this.dueDate = saveLoan.getDueDate();
//        this.status = saveLoan.getStatus();
//        this.applicationDate = saveLoan.getApplicationDate();
//    }
}
