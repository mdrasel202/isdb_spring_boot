package com.rasel.bank_management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rasel.bank_management.constants.AccountStatus;
import com.rasel.bank_management.constants.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B_BANK_ACCOUNT")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Card> cards;

//    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Transactions> transactions;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true,  fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Transaction> transaction;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Loan> loans;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Deposit> deposits;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(name = "available_balance", nullable = false)
    private BigDecimal availableBalance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "opened_date", nullable = true)
    private LocalDate openedDate;

    @Column(nullable = false)
    private LocalDate requestDate;

    @Column(nullable = false)
    private BigDecimal balance;

    @Column(nullable = false)
    private String name;
}

