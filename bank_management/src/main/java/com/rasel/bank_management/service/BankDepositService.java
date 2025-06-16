package com.rasel.bank_management.service;

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
        return null;
    }
}
