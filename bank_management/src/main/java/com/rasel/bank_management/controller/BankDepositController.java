package com.rasel.bank_management.controller;

import com.rasel.bank_management.constants.BankDepositStatus;
import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.dto.BankDepositRequestDTO;
import com.rasel.bank_management.dto.BankDepositResponseDTO;
import com.rasel.bank_management.service.BankDepositService;
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

    @GetMapping("/getAll")
    public ResponseEntity<List<BankDepositResponseDTO>> getAll(){
        List<BankDepositResponseDTO> lists = bankDepositService.getAll();
        return ResponseEntity.ok(lists);
    }

    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<BankDepositResponseDTO>> getAll(@PathVariable String accountNumber){
        return ResponseEntity.ok(bankDepositService.getAlls(accountNumber));
    }

    //Admin approve deposit
    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approve(@PathVariable Long id){
        bankDepositService.updateStatus(id, BankDepositStatus.APPROVED);
        return ResponseEntity.ok("Deposit approved");
    }

    //Admin cancels deposit
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancel(@PathVariable Long id){
        bankDepositService.updateStatus(id, BankDepositStatus.CANCELLED);
        return ResponseEntity.ok("Deposit cancelled");
    }

    //Admin view all pending deposit
     @GetMapping("/pending")
    public ResponseEntity<List<BankDepositResponseDTO>> panding(){
        return ResponseEntity.ok(bankDepositService.getPending());
     }
}
