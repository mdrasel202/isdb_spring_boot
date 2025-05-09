package com.rasel.bank_management.repository;

import com.rasel.bank_management.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {
}
