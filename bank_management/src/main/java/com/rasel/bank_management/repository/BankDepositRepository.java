package com.rasel.bank_management.repository;

import com.rasel.bank_management.constants.BankDepositStatus;
import com.rasel.bank_management.model.BankDeposit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankDepositRepository extends JpaRepository<BankDeposit, Long> {
    List<BankDeposit> findByBankAccount_AccountNumber(String accountNumber);
    List<BankDeposit> findByBankDepositStatus(BankDepositStatus bankDepositStatus);
}
