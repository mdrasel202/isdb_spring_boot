package com.rasel.bank_management.service;

import com.rasel.bank_management.dto.WithdrawRequestDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.WithdrawalTransaction;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.WithdrawalTransactionRepositotory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class WithdrawalService {
    @Autowired
    private WithdrawalTransactionRepositotory withdrawalTransactionRepositotory;

    @Autowired
    private AccountRepository accountRepository;

    //Post All
    public Object withdraw(WithdrawRequestDTO withdrawRequestDTO) {
        BankAccount account = accountRepository.findByAccountNumber(withdrawRequestDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        BigDecimal balance = account.getBalance();

        if(balance.compareTo(withdrawRequestDTO.getAmount()) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(balance.subtract(withdrawRequestDTO.getAmount()));
        account.setAvailableBalance(account.getAvailableBalance().subtract(withdrawRequestDTO.getAmount()));
        accountRepository.save(account);

        //save withdrawal history
        WithdrawalTransaction withdrawalTransaction = new WithdrawalTransaction();
        withdrawalTransaction.setAccountNumber(withdrawRequestDTO.getAccountNumber());
        withdrawalTransaction.setAmount(withdrawRequestDTO.getAmount());
        withdrawalTransaction.setTimestamp(LocalDateTime.now());
        withdrawalTransaction.setBankAccount(account);
        withdrawalTransactionRepositotory.save(withdrawalTransaction);

        return "Withdrawal of" + withdrawRequestDTO.getAmount()+ "Successful";
    }

    //Get Account
    public List<WithdrawalTransaction> getWithdrawal(String accountNumber) {
        return withdrawalTransactionRepositotory.findByAccountNumber(accountNumber);
    }

    //Get All
    public List<WithdrawalTransaction> getAll() {
        return withdrawalTransactionRepositotory.findAll();
    }
}
