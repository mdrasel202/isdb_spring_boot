package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.LoanRequestDTO;
import com.rasel.bank_management.dto.LoanResponseDTO;
import com.rasel.bank_management.model.Loan;
import com.rasel.bank_management.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = ("/loans"))
public class LoanController {

    private final LoanService loanService;

    public LoanController(LoanService loanService){
        this.loanService = loanService;
    }

    /**
     * Endpoint for users to request a new loan.
     * POST /api/loans/request/{userId}
     */
    @PostMapping("/request")
    public ResponseEntity<LoanResponseDTO> requestLoan( @Valid @RequestBody LoanRequestDTO requestDTO){
        LoanResponseDTO responseDTO = loanService.requestLoan(requestDTO);
        return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
    }

    /**
     * Admin creates a loan manually.
     * POST /api/loans/admin/create
     */
    @PostMapping("/request/create")
    public ResponseEntity<?>  adminCrateLoan(@Valid @RequestBody LoanRequestDTO requestDTO){
//        LoanResponseDTO responseDTO = loanService.admonCrateLoan(requestDTO);
        return ResponseEntity.ok(loanService.adminCrateLoan(requestDTO));
    }

    /**
     * Get all loans in the system (admin view).
     * GET /api/loans/admin/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<Loan>> getAllLoans(){
        List<Loan> loans = loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    /**
     * Get loan summary (totals, counts, etc).
     * GET /api/loans/admin/summary
     */
    @GetMapping("/admin/summary")
    public ResponseEntity<Map<String, Object>> getLoanSummary() {
        return ResponseEntity.ok(loanService.getLoanSummary());
    }

    /**
     * Get all loans for a specific user.
     * GET /api/loans/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Loan>> getLoanId(@PathVariable Integer userId){
        return ResponseEntity.ok(loanService.getLoanId(userId));
    }

    /**
     * Get loan details by loan ID.
     * GET /api/loans/{loanId}
     */
    @GetMapping("/{loanId}")
    public ResponseEntity<?> getLoansId(@PathVariable Long loanId){
        return ResponseEntity.ok(loanService.getLoansId(loanId));
    }

}
