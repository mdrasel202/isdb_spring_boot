package com.rasel.bank_management.repository;

import com.rasel.bank_management.constants.LoanStatus;
import com.rasel.bank_management.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Integer userId);

    List<Loan> findByStatus(LoanStatus status);
}
