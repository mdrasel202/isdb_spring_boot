package com.rasel.bank_management.repository;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CardRepository extends JpaRepository<Card, Long> {
    // If 'bankAccount' is an object reference:
    List<Card> findByBankAccount(BankAccount bankAccount);

    List<Card> findByStatusIn(List<CardStatus> status);

    // If 'bankAccountId' is a Long field in the Card entity:
    Optional<Card> findByBankAccountId(Long bankAccountId);
}
