package com.rasel.bank_management.service;

import com.rasel.bank_management.constants.LoanStatus;
import com.rasel.bank_management.dto.LoanRequestDTO;
import com.rasel.bank_management.dto.LoanResponseDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.Loan;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.LoanRepository;
import com.rasel.bank_management.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    public LoanService(LoanRepository loanRepository, UserRepository userRepository, AccountRepository accountRepository){
        this.loanRepository = loanRepository;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    /**
     * Endpoint for users to request a new loan.
     * POST /api/loans/request/{userId}
     */
    public LoanResponseDTO requestLoan(LoanRequestDTO requestDTO) {
        User user = userRepository.findById(requestDTO.getUserId()).orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount bankAccount = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Bank account not found"));

        Loan loan = new Loan(); 
        loan.setUser(user);
        loan.setBankAccount(bankAccount);
        loan.setAmount(requestDTO.getAmount());
        loan.setInterestRate(requestDTO.getInterestRate());
        loan.setDueDate(requestDTO.getDueDate());
        loan.setStatus(LoanStatus.PENDING);

        Loan saveLoan = loanRepository.save(loan);

        return new LoanResponseDTO(saveLoan);
    }

    /**
     * Admin creates a loan manually.
     * POST /api/loans/admin/create
     */
    public Loan adminCrateLoan(@Valid LoanRequestDTO requestDTO) {
       User user = userRepository.findById(requestDTO.getUserId()).orElseThrow(()-> new RuntimeException("User not found"));
       BankAccount bankAccount = accountRepository.findByAccountNumber(requestDTO.getAccountNumber()).orElseThrow(()-> new RuntimeException("Back account not found"));

       Loan loan = new Loan();
       loan.setUser(user);
       loan.setBankAccount(bankAccount);
       loan.setAmount(requestDTO.getAmount());
       loan.setApprovedAmount(requestDTO.getAmount()); // Admin-approved
         loan.setInterestRate(requestDTO.getInterestRate());
         loan.setDueDate(requestDTO.getDueDate());
         loan.setStatus(LoanStatus.PAID);
         loan.setApplicationDate(LocalDate.now());
         loan.setAcceptDate(LocalDate.now());
         loan.setAvailableAmount(requestDTO.getAmount());

         return loanRepository.save(loan);
    }

    /**
     * Get all loans in the system (admin view).
     * GET /api/loans/admin/all
     */
    public List<Loan> getAllLoans() {
        List<Loan> loans = loanRepository.findAll();
        return loans;
    }

    /**
     * Get loan summary (totals, counts, etc).
     * GET /api/loans/admin/summary
     */
    public Map<String, Object> getLoanSummary() {
        return null;
    }

    /**
     * Get all loans for a specific user.
     * GET /api/loans/user/{userId}
     */
    public List<Loan> getLoanId(Integer userId) {
       return loanRepository.findByUserId(userId);
    }

    /**
     * Get loan details by loan ID.
     * GET /api/loans/{loanId}
     */
    public Loan getLoansId(Long loanId) {
        return loanRepository.findById(loanId).orElseThrow(() -> new RuntimeException("Loan not found"));
    }


}
