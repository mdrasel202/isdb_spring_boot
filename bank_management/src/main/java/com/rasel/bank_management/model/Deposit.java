package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.DepositStatus;
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
@Table(name = "B_DEPOSITS")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(name = "deposit_date")
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private DepositStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private BankAccount bankAccount;

    @PrePersist
    public void prePersist() {
        this.date = LocalDate.now();
        this.status = DepositStatus.COMPLETED;
    }
}
