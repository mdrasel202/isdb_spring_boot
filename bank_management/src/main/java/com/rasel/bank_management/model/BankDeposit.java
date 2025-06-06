package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.BankDepositStatus;
import com.rasel.bank_management.constants.DepositInterestRate;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.internal.util.privilegedactions.LoadClass;

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
    private DepositInterestRate interestRate;

    @Column(nullable = false)
    private BigDecimal interestEarned;

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
