package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.DepositStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B_DEPOSIT")
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private DepositStatus status;

    @ManyToOne
    @JoinColumn(name = "accout_id")
    private BankAccount bankAccount;

    @PrePersist
    public void prePersist(){
        this.date = LocalDate.now();
        this.status = DepositStatus.COMPLETED;
    }
}
