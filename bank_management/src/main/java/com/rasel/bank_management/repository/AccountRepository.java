package com.rasel.bank_management.repository;

import com.rasel.bank_management.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {
}
