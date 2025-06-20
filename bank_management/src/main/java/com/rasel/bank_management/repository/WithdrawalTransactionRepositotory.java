package com.rasel.bank_management.repository;

import com.rasel.bank_management.model.WithdrawalTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalTransactionRepositotory extends JpaRepository<WithdrawalTransaction, Long> {
    List<WithdrawalTransaction> findByAccountNumber(String accountNumber);
}
