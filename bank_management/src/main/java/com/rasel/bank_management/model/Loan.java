package com.rasel.bank_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.rasel.bank_management.constants.LoanStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "B_LOAN")
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "account_number", nullable = false)
    private BankAccount bankAccount;

    private BigDecimal amount;

    @Column(nullable = false)
    private BigDecimal approvedAmount;

    private BigDecimal interestRate;

    private BigDecimal availableAmount;

    private LocalDate applicationDate;

    private LocalDate acceptDate;

    private LocalDate dueDate;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;

//    private BigDecimal monthlyInterest;
//
//    private BigDecimal yearlyInterest;


    @PrePersist
    public void prePersist() {
        if (applicationDate == null) {
            applicationDate = LocalDate.now();
        }
    }
}
