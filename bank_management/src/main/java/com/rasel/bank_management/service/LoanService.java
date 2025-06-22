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
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

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
        loan.setApprovedAmount(BigDecimal.ZERO);
        loan.setInterestRate(requestDTO.getInterestRate());
        loan.setAvailableAmount(BigDecimal.ZERO);
        loan.setApplicationDate(requestDTO.getApplicationDate());
        loan.setAcceptDate(LocalDate.now());
        loan.setDueDate(requestDTO.getDueDate());
        loan.setStatus(LoanStatus.PENDING);

        // Calculate monthly and yearly interest based on approved amount (0.00 at request time)
        BigDecimal yearlyInterest = BigDecimal.ZERO;
        BigDecimal monthlyInterest = BigDecimal.ZERO;

        loan.setYearlyInterest(yearlyInterest);
        loan.setMonthlyInterest(monthlyInterest);

        return  saveLoan(loanRepository.save(loan));

//        return new LoanResponseDTO(saveLoan);
    }

    /**
     * Get all loans for a specific user.
     * GET /api/loans/user/{userId}
     */
    public List<LoanResponseDTO> getUserId(Integer userId) {
        return loanRepository.findByUserId(userId)
                .stream()
                .map(this::saveLoan)
                .collect(Collectors.toList());
    }

    //pending
    public List<LoanResponseDTO> getPendingLoans() {
        return loanRepository.findByStatus(LoanStatus.PENDING)
                .stream()
                .map(this::saveLoan)
                .collect(Collectors.toList());
    }

    //approved
    public LoanResponseDTO postApprovedLoan(Long loanId, BigDecimal approvedAmount) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.APPROVED);
        loan.setApprovedAmount(approvedAmount);
        loan.setAvailableAmount(approvedAmount);
        loan.setAcceptDate(LocalDate.now());


        BigDecimal interestRate = loan.getInterestRate() != null ? loan.getInterestRate() : BigDecimal.ZERO;
        BigDecimal yearlyInterest = approvedAmount.multiply(interestRate).divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
        BigDecimal monthlyInterest = yearlyInterest.divide(BigDecimal.valueOf(12), RoundingMode.HALF_UP);

        loan.setYearlyInterest(yearlyInterest);
        loan.setMonthlyInterest(monthlyInterest);

        return saveLoan(loanRepository.save(loan));
    }
    
    //cancle
    public LoanResponseDTO CancelLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        loan.setStatus(LoanStatus.CANCELLED);

        return saveLoan(loanRepository.save(loan));
    }

    private LoanResponseDTO saveLoan(Loan loan){
        LoanResponseDTO dto = new LoanResponseDTO();
        dto.setLoanId(loan.getId());
        dto.setAccountNumber(loan.getBankAccount().getAccountNumber());
        dto.setAmount(loan.getAmount());
        dto.setApprovedAmount(loan.getApprovedAmount());
        dto.setInterestRate(loan.getInterestRate());
        dto.setAvailableAmount(loan.getAvailableAmount());
        dto.setDueDate(loan.getDueDate());
        dto.setAcceptDate(loan.getAcceptDate());
        dto.setStatus(loan.getStatus());
        dto.setApplicationDate(loan.getApplicationDate());
        dto.setMonthlyInterest(loan.getMonthlyInterest());

        dto.setYearlyInterest(loan.getYearlyInterest());

        return dto;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

}
