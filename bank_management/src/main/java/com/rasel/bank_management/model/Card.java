package com.rasel.bank_management.model;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.constants.CardType;
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

    @Enumerated(value = EnumType.STRING)
    private CardType card;

    private LocalDate expiry_date;

    @Enumerated(value = EnumType.STRING)
    private CardStatus status;

    @ManyToOne
    @JoinColumn(name = "back account", nullable = false)
    private BankAccount bankAccount;
}
