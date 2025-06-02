package com.rasel.bank_management.service;

import com.rasel.bank_management.dto.DepositResponseDTO;
import com.rasel.bank_management.dto.DepostRequestDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.Deposit;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.DepositRepository;
import org.springframework.stereotype.Service;

@Service
public class DepositService {
    private final DepositRepository depositRepository;
    private final AccountRepository accountRepository;

    public DepositService (DepositRepository depositRepository, AccountRepository accountRepository){
        this.depositRepository = depositRepository;
        this.accountRepository = accountRepository;
    }
    //post
    public DepositResponseDTO request(DepostRequestDTO depostRequestDTO) {
        BankAccount bankAccount = accountRepository.findByAccountNumber(depostRequestDTO.getAccountNumber()).orElseThrow(() -> new RuntimeException("Bank account not found"));

        // Create deposit
        Deposit deposit = new Deposit();
        deposit.setAmount(depostRequestDTO.getAmount());
        deposit.setBankAccount(bankAccount);

        // Save deposit
        Deposit savedDeposit = depositRepository.save(deposit);

        // Update account balance
        bankAccount.setAvailableBalance(bankAccount.getAvailableBalance().add(depostRequestDTO.getAmount()));
        accountRepository.save(bankAccount);

        // Prepare response
        DepositResponseDTO responseDTO = new DepositResponseDTO();
        responseDTO.setId(savedDeposit.getId());
        responseDTO.setAmount(savedDeposit.getAmount());
        responseDTO.setAccountNumber(bankAccount.getAccountNumber());
        responseDTO.setDate(savedDeposit.getDate());
        responseDTO.setStatus(savedDeposit.getStatus());

        return responseDTO;
    }
}
