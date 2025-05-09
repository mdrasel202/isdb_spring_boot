package com.rasel.bank_management.repository;

import com.rasel.bank_management.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {
}
