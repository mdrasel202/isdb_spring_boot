package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.BankDepositInterestRate;
import com.rasel.bank_management.constants.BankDepositStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B_BANK_DEPOSIT")
public class BankDeposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal depositAmount;

    @Enumerated(EnumType.STRING)
    private BankDepositInterestRate bankDepositInterestRate;

    @Column(nullable = false)
    private BigDecimal interestEarned;

    private double interestRate;

    @Column(nullable = false)
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    private BankDepositStatus bankDepositStatus = BankDepositStatus.PENDING;

    @Column(nullable = false)
    private LocalDate maturityDatel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount bankAccount;
}
