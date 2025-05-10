package com.rasel.bank_management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
//    @JoinColumn(name = "card", nullable = false)
    private List<Card> card;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AccountType type;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private AccountStatus status;

    @Column(nullable = false)
    private BigDecimal availableBalance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate openedDate;

}
