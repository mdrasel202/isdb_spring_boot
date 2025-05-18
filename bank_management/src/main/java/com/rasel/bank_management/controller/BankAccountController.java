package com.rasel.bank_management.controller;

import com.rasel.bank_management.constants.Role;
import com.rasel.bank_management.dto.BankAccountRequestDTO;
import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.dto.TransferRequestDTO;
import com.rasel.bank_management.exception.EmailSendingException;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.Transaction;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.service.AccountService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/bank")
public class BankAccountController {
    private final AccountService accountService;

    public BankAccountController(AccountService accountService){
        this.accountService = accountService;
    }

    // USER REQUESTS ACCOUNT
    @PostMapping("/request")
    public ResponseEntity<Map<String, String>> requestAccount(@RequestBody BankAccountRequestDTO dto) {
        accountService.requestBankAccount(dto);
        return ResponseEntity.ok(Map.of("message", "Bank account request submitted."));
    }

    // ADMIN GETS REQUESTS
    @GetMapping("/requests")
    public ResponseEntity<List<BankAccountResponseDTO>> getAllRequests() {
        return ResponseEntity.ok(accountService.getAllRequestedAccounts());
    }

    // ADMIN APPROVES ACCOUNT
    @PostMapping("/approve/{id}")
    public ResponseEntity<BankAccountResponseDTO> approveAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.approveAccount(id));
    }

    //update
//    @PutMapping("/{id}/status")
//    public ResponseEntity<BankAccountResponseDTO> updateStatus(
//            @PathVariable Long id,
//            @RequestBody Map<String, String> payload) {
//
//        String status = payload.get("status");
//        BankAccountResponseDTO updated = accountService.updateAccountStatus(id, status);
//        return ResponseEntity.ok(updated);
//    }


    //Transation
    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody TransferRequestDTO request) {
        accountService.transferBalance(request);
        return ResponseEntity.ok("Transfer successful");
    }

    @GetMapping("/{accountId}/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(@PathVariable Long accountId) {
        List<Transaction> transactions = accountService.getTransactionsForAccount(accountId);
        return ResponseEntity.ok(transactions);
    }

}
