package com.rasel.bank_management.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.constants.CardType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "B_CARD")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false, unique = true)

    private String cardNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CardType card;

    @Column(nullable = false)
    private LocalDate expiry_date;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CardStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "back_account_id", nullable = false)
    @JsonBackReference
    private BankAccount bankAccount;

    // Timestamp when the card was created
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Timestamp when the card was last updated
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Automatically set timestamps
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
