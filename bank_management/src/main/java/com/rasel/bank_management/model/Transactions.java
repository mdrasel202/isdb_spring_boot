package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.TransactionType;
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
@Table(name = "B_TRANSACTION")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID") // Explicitly define name for clarity
    private Long id;

    @Column(name = "TXN_ID", unique = true, nullable = false)
    private String txnId;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private BankAccount bankAccount;

    @Enumerated(EnumType.STRING)
    @Column(name = "TXN_TYPE", nullable = false)
    private TransactionType txnType;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "BALANCE_AFTER")
    private BigDecimal balanceAfter;

    @Column(name = "TXN_DATE")
    private LocalDate date;
}
