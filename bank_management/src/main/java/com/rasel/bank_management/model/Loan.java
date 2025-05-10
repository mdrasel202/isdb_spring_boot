package com.rasel.bank_management.model;

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

    @Column(nullable = false)
    private String accountNumber;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal amount;

    private BigDecimal approvedAmount;

    private BigDecimal interestRate;

    private BigDecimal availableAmount;

    private LocalDate applicationDate;

    private LocalDate dueDate;

    @Enumerated(value = EnumType.STRING)
    private LoanStatus status;
}
