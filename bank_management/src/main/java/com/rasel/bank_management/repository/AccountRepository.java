package com.rasel.bank_management.repository;

import com.rasel.bank_management.constants.AccountStatus;
import com.rasel.bank_management.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    List<BankAccount> findByStatus(AccountStatus status);
}
