package com.rasel.bank_management.controller;

import com.rasel.bank_management.constants.Role;
import com.rasel.bank_management.dto.BankAccountRequest;
import com.rasel.bank_management.exception.EmailSendingException;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.service.AccountService;
import com.rasel.bank_management.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/bank")
public class BankAccountController {
    private final AccountService accountService;
    private final EmailService emailService;

    public BankAccountController(AccountService accountService, EmailService emailService){
        this.accountService = accountService;
        this.emailService = emailService;
    }

    //get id
    @GetMapping("/{id}")
      public ResponseEntity<BankAccount> getId(@PathVariable Long id){
        BankAccount bankAccount = accountService.getId(id);
        return new ResponseEntity<>(bankAccount,HttpStatus.OK);
     }

     //post
    @PostMapping("/{account}")
    public ResponseEntity<BankAccount> saveAccount(@Valid @RequestBody BankAccountRequest bankAccountRequest){
        User user = new User();
        user.setFirstName(bankAccountRequest.getFirstName());
        user.setLastName(bankAccountRequest.getLastName());
        user.setPhone(bankAccountRequest.getPhone());
//        user.setEmail(bankAccountRequest.getEmail());
        user.setPhone(bankAccountRequest.getPhone());
        user.setAddress(bankAccountRequest.getAddress());
        user.setRole(Role.USER);


       BankAccount bankAccount = new BankAccount();
       bankAccount.setAccountNumber(bankAccountRequest.getAccountNumber());
       bankAccount.setAvailableBalance(bankAccountRequest.getAvailableBalance());
//       bankAccount.


        try {
            emailService.sendEmailWithAttachment("himusharier@gmail.com", "welcome email", "your account has been created.");
        } catch (RuntimeException e) {
            throw new EmailSendingException("email sending failed!");
        }
        return new ResponseEntity<>(accountService.saveAccount(bankAccount), HttpStatus.OK);
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
    @PutMapping
    public ResponseEntity<BankAccount> update(@PathVariable Long id, @RequestBody BankAccount bankAccount){
        BankAccount updateAccount = accountService.update(id, bankAccount);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }
}
