package com.rasel.bank_management.controller;

import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bank")
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    //get id
    @GetMapping("/{id}")
      public ResponseEntity<BankAccount> getId(@PathVariable Long id){
        BankAccount bankAccount = accountService.getId(id);
        return new ResponseEntity<>(bankAccount,HttpStatus.OK);
     }

     //post
    @PostMapping("/{account}")
    public ResponseEntity<BankAccount> saveAccount(@PathVariable BankAccount bankAccount){
        BankAccount save = accountService.saveAccount(bankAccount);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    //get All
    @GetMapping
    public ResponseEntity<List<BankAccount>> getAll(){
        List<BankAccount> getall = accountService.getAll();
        return new ResponseEntity<>(getall, HttpStatus.OK);
    }

    //delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteId(@PathVariable Long id){
        accountService.deleteId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //put
    public ResponseEntity<BankAccount> update(@PathVariable Long id, @RequestBody BankAccount bankAccount){
        BankAccount updateAccount = accountService.update(id, bankAccount);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }
}
