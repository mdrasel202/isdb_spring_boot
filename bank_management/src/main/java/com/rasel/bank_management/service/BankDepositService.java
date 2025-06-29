package com.rasel.bank_management.service;

import com.rasel.bank_management.constants.BankDepositStatus;
import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.dto.BankDepositRequestDTO;
import com.rasel.bank_management.dto.BankDepositResponseDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.BankDeposit;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.BankDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankDepositService {

    @Autowired
    private BankDepositRepository bankDepositRepository;

    @Autowired
    private AccountRepository accountRepository;

    //Bank Deposit Request
    public BankDepositResponseDTO request(BankDepositRequestDTO requestDTO) {
        BankAccount bankAccount = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        bankAccount.setAvailableBalance(bankAccount.getAvailableBalance().add(requestDTO.getDepositAmount()));
        accountRepository.save(bankAccount);

        LocalDate start = LocalDate.now();

        BankDeposit bankDeposit = new BankDeposit();
        bankDeposit.setBankAccount(bankAccount);
        bankDeposit.setDepositAmount(requestDTO.getDepositAmount());
        bankDeposit.setBankDepositStatus(requestDTO.getBankDepositStatus());
        bankDeposit.setDepositDate(start);
        bankDepositRepository.save(bankDeposit);

        BankDepositResponseDTO response = new BankDepositResponseDTO();
        response.setId(bankDeposit.getId());
        response.setAccountNumber(bankAccount.getAccountNumber());
        response.setDepositAmount(bankDeposit.getDepositAmount());
        response.setBankDepositStatus(bankDeposit.getBankDepositStatus());
        response.setDepositDate(bankDeposit.getDepositDate());
        return response;
    }

    //Get All
    public List<BankDepositResponseDTO> getAlls(String accountNumber) {
        return bankDepositRepository.findByBankAccount_AccountNumber(accountNumber).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //Admin approve deposit  Admin cancels deposit
//    public void updateStatus(Long id, BankDepositStatus bankDepositStatus) {
//        BankDeposit deposit = bankDepositRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Deposit not found"));
//        deposit.setBankDepositStatus(bankDepositStatus);
//        bankDepositRepository.save(deposit);
//    }

//    //Pending
//    public List<BankDepositResponseDTO> getPending() {
//        return bankDepositRepository.findByBankDepositStatus(BankDepositStatus.PENDING).stream()
//                .map(this::toDTO)
//                .collect(Collectors.toList());
//    }

    private BankDepositResponseDTO toDTO(BankDeposit deposit) {
        BankDepositResponseDTO response = new BankDepositResponseDTO();
        response.setId(deposit.getId());
        response.setAccountNumber(deposit.getBankAccount().getAccountNumber());
        response.setDepositAmount(deposit.getDepositAmount());
        response.setBankDepositStatus(deposit.getBankDepositStatus());
        response.setDepositDate(deposit.getDepositDate());
        return response;
    }

    //Get All
    public List<BankDepositResponseDTO> getAll() {
        return bankDepositRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}

