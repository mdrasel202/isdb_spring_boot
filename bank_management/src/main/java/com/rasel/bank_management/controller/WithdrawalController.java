package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.WithdrawRequestDTO;
import com.rasel.bank_management.model.WithdrawalTransaction;
import com.rasel.bank_management.service.WithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/withdrawal")
public class WithdrawalController {

    @Autowired
    private WithdrawalService withdrawalService;

    @PostMapping
    private ResponseEntity<?> withdraw(@RequestBody WithdrawRequestDTO withdrawRequestDTO){
        try{
            return ResponseEntity.ok(withdrawalService.withdraw(withdrawRequestDTO));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{accountNumber}")
    private List<WithdrawalTransaction> getUserWithdrawal(@PathVariable String accountNumber){
        List<WithdrawalTransaction> getUsers = withdrawalService.getWithdrawal(accountNumber);
        return getUsers;
    }

    @GetMapping("/admin")
    private List<WithdrawalTransaction> getAll(){
        return withdrawalService.getAll();
    }
}
