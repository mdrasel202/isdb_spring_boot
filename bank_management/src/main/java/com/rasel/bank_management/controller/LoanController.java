package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.LoanApprovedDTO;
import com.rasel.bank_management.dto.LoanRequestDTO;
import com.rasel.bank_management.dto.LoanResponseDTO;
import com.rasel.bank_management.model.Loan;
import com.rasel.bank_management.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
     * Get all loans for a specific user.
     * GET /api/loans/user/{userId}
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LoanResponseDTO>> getLoanId(@PathVariable Integer userId){
        return ResponseEntity.ok(loanService.getUserId(userId));
    }

    //pending
    @GetMapping("/pending")
    public ResponseEntity<List<LoanResponseDTO>> getPaddingLoans(){
        return ResponseEntity.ok(loanService.getPendingLoans());
    }

    //approved
    @PostMapping("/approved/{id}")
    public ResponseEntity<LoanResponseDTO> postApproved(@PathVariable Long id, @RequestBody LoanApprovedDTO loanApprovedDTO){
        LoanResponseDTO postAppr = loanService.postApprovedLoan(id,loanApprovedDTO.getAmount());
        return new ResponseEntity<>(postAppr, HttpStatus.OK);
    }

    //cancle
    @PostMapping("/cancel/{id}")
    public ResponseEntity<LoanResponseDTO> postCancel(@PathVariable Long id){
        return ResponseEntity.ok(loanService.CancelLoan(id));
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

//    /**
//     * Admin creates a loan manually.
//     * POST /api/loans/admin/create
//     */
//    @PostMapping("/request/create")
//    public ResponseEntity<?>  adminCrateLoan(@Valid @RequestBody LoanRequestDTO requestDTO){
////        LoanResponseDTO responseDTO = loanService.admonCrateLoan(requestDTO);
//        return ResponseEntity.ok(loanService.adminCrateLoan(requestDTO));
//    }
//
//
//    /**
//     * Get loan summary (totals, counts, etc).
//     * GET /api/loans/admin/summary
//     */
//    @GetMapping("/admin/summary")
//    public ResponseEntity<Map<String, Object>> getLoanSummary() {
//        return ResponseEntity.ok(loanService.getLoanSummary());
//    }
//
//
//
//    /**
//     * Get loan details by loan ID.
//     * GET /api/loans/{loanId}
//     */
//    @GetMapping("/{loanId}")
//    public ResponseEntity<?> getLoansId(@PathVariable Long loanId){
//        return ResponseEntity.ok(loanService.getLoansId(loanId));
//    }

}
