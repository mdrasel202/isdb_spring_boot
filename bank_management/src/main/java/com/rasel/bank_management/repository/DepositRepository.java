package com.rasel.bank_management.repository;

import com.rasel.bank_management.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepositRepository extends JpaRepository<Deposit , Long> {
}
