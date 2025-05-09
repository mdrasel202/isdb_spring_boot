package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private String cardNumber;
    private com.rasel.bank_management.constants.Card card;
    private LocalDate expiry_date;
    private CardStatus status;

    @ManyToOne
    @JoinColumn(name = "back account", nullable = false)
    private BankAccount bankAccount;
}
