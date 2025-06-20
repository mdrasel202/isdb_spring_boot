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
        if(bankAccount.getBalance().compareTo(requestDTO.getDepositAmount()) < 0){
            throw new RuntimeException("Insufficient balance");
        }

        bankAccount.setBalance(bankAccount.getBalance().subtract(requestDTO.getDepositAmount()));
        accountRepository.save(bankAccount);

        double rate = requestDTO.getBankDepositInterestRate().getRate();
        String rateLabel = requestDTO.getBankDepositInterestRate().getLabel();
        LocalDate start = LocalDate.now();
        LocalDate maturity = start.plusYears(1);

        BigDecimal interest = requestDTO.getDepositAmount()
                .multiply(BigDecimal.valueOf(rate))
                .setScale(2, RoundingMode.HALF_UP);

        BankDeposit bankDeposit = new BankDeposit();
        bankDeposit.setBankAccount(bankAccount);
        bankDeposit.setDepositAmount(requestDTO.getDepositAmount());
        bankDeposit.setBankDepositInterestRate(requestDTO.getBankDepositInterestRate());
        bankDeposit.setInterestRate(rate);
        bankDeposit.setInterestEarned(interest);
        bankDeposit.setBankDepositStatus(requestDTO.getBankDepositStatus());
        bankDeposit.setStartDate(start);
        bankDeposit.setMaturityDatel(maturity);
        bankDepositRepository.save(bankDeposit);

        BankDepositResponseDTO response = new BankDepositResponseDTO();
        response.setId(bankDeposit.getId());
        response.setAccountNumber(bankAccount.getAccountNumber());
        response.setDepositAmount(bankDeposit.getDepositAmount());
        response.setInterestRate(rate);
        response.setInterestRateLabel(rateLabel);
        response.setInterestEarned(interest);
        response.setBankDepositStatus(bankDeposit.getBankDepositStatus());
        response.setStartDate(start);
        response.setMaturityDatel(maturity);

        return response;
    }

    //Get All
    public List<BankDepositResponseDTO> getAlls(String accountNumber) {
        return bankDepositRepository.findByBankAccount_AccountNumber(accountNumber).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //Admin approve deposit  Admin cancels deposit
    public void updateStatus(Long id, BankDepositStatus bankDepositStatus) {
        BankDeposit deposit = bankDepositRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Deposit not found"));
        deposit.setBankDepositStatus(bankDepositStatus);
        bankDepositRepository.save(deposit);
    }

    //Pending
    public List<BankDepositResponseDTO> getPending() {
        return bankDepositRepository.findByBankDepositStatus(BankDepositStatus.PENDING).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private BankDepositResponseDTO toDTO(BankDeposit deposit) {
        BankDepositResponseDTO response = new BankDepositResponseDTO();
        response.setId(deposit.getId());
        response.setAccountNumber(deposit.getBankAccount().getAccountNumber());
        response.setDepositAmount(deposit.getDepositAmount());
        response.setInterestRate(deposit.getInterestRate());

        if (deposit.getBankDepositInterestRate() != null) {
            response.setInterestRateLabel(deposit.getBankDepositInterestRate().getLabel());
        } else {
            response.setInterestRateLabel(deposit.getInterestRate() * 100 + "%");
        }

        response.setInterestEarned(deposit.getInterestEarned());
        response.setBankDepositStatus(deposit.getBankDepositStatus());
        response.setStartDate(deposit.getStartDate());
        response.setMaturityDatel(deposit.getMaturityDatel());
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

