package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.dto.BankDepositRequestDTO;
import com.rasel.bank_management.dto.BankDepositResponseDTO;
import com.rasel.bank_management.service.BankDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bankdeposit")
public class BankDepositController {
    private final BankDepositService bankDepositService;

    public BankDepositController(BankDepositService bankDepositService) {
        this.bankDepositService = bankDepositService;
    }

    @PostMapping("/create")
    public ResponseEntity<BankDepositResponseDTO> requests(@RequestBody BankDepositRequestDTO requestDTO){
//        BankDepositResponseDTO getRequests = bankDepositService.requests(requestDTO);
        return ResponseEntity.ok(bankDepositService.request(requestDTO));
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<BankDepositResponseDTO>> getAll(@PathVariable String accountNumber){
        return ResponseEntity.ok(bankDepositService.getAlls(accountNumber));
    }
}
