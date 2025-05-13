package com.rasel.bank_management.controller;

import com.rasel.bank_management.constants.Role;
import com.rasel.bank_management.dto.BankAccountRequest;
import com.rasel.bank_management.exception.EmailSendingException;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.service.AccountService;
import com.rasel.bank_management.service.EmailService;
import com.rasel.bank_management.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@RestController
@RequestMapping(value = "/bank")
//@CrossOrigin(origins = "http://localhost:4200")
public class BankAccountController {
    private final AccountService accountService;
    private final EmailService emailService;
    private final UserService userService;

    public BankAccountController(AccountService accountService,
                                 EmailService emailService,
                                 UserService userService){
        this.accountService = accountService;
        this.emailService = emailService;
        this.userService = userService;
    }

    //get id
    @GetMapping("/{id}")
      public ResponseEntity<BankAccount> getId(@PathVariable Long id){
        BankAccount bankAccount = accountService.getId(id);
        return new ResponseEntity<>(bankAccount,HttpStatus.OK);
     }

     //post
    /*@PostMapping
    public ResponseEntity<BankAccount> saveAccount(@Valid @RequestBody BankAccountRequest bankAccountRequest)
            throws MessagingException, GeneralSecurityException, IOException {
        User user = new User();
        user.setFirstName(bankAccountRequest.getFirstName());
        user.setLastName(bankAccountRequest.getLastName());
        user.setPhone(bankAccountRequest.getPhone());
//        user.setEmail("placeholder@example.com");// Or request.getEmail() if you include it
        user.setEmail(bankAccountRequest.getEmail());
        user.setPhone(bankAccountRequest.getPhone());
        user.setAddress(bankAccountRequest.getAddress());
        user.setPassword("12340"); // In real app, encode and set
        user.setRole(Role.USER);

        User savedUser = userService.createUser(user);

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(bankAccountRequest.getAccountNumber());
        bankAccount.setAvailableBalance(bankAccountRequest.getAvailableBalance());
        bankAccount.setOpenedDate(bankAccountRequest.getOpenedDate());
        bankAccount.setStatus(bankAccountRequest.getAccountStatus());
        bankAccount.setType(bankAccountRequest.getAccountType());
        bankAccount.setUser(savedUser);
//       bankAccount.
        BankAccount savedAccount = accountService.saveAccount(bankAccount);

        try {
            emailService.sendEmailWithAttachment("raselabc204@gmail.com", "welcome email", "your account has been created.");
        } catch (RuntimeException e) {
            System.out.println("Email sending failed"+e.getMessage());
            throw new EmailSendingException("email sending failed!");
        }
        return new ResponseEntity<>(accountService.saveAccount(bankAccount), HttpStatus.OK);
    }*/

    @PostMapping
    public ResponseEntity<BankAccount> saveAccount(@Valid @RequestBody BankAccountRequest bankAccountRequest)
            throws MessagingException, GeneralSecurityException, IOException {

        String email = bankAccountRequest.getEmail();
        User user = userService.findByEmail(email); // You'll need to implement this method if not already present

//        User user = new User();

        if (user == null) {
            user = new User();
            user.setFirstName(bankAccountRequest.getFirstName());
            user.setLastName(bankAccountRequest.getLastName());
            user.setPhone(bankAccountRequest.getPhone());
            user.setEmail(email);
//            user.setEmail(bankAccountRequest.getEmail());
            user.setAddress(bankAccountRequest.getAddress());
            user.setPassword("12340"); // Ideally encode this
            user.setRole(Role.USER);

            user = userService.createUser(user); // save new user
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountNumber(bankAccountRequest.getAccountNumber());
        bankAccount.setAvailableBalance(bankAccountRequest.getAvailableBalance());
        bankAccount.setOpenedDate(bankAccountRequest.getOpenedDate());
        bankAccount.setStatus(bankAccountRequest.getAccountStatus());
        bankAccount.setType(bankAccountRequest.getAccountType());
        bankAccount.setUser(user); // use either new or existing user

        BankAccount savedAccount = accountService.saveAccount(bankAccount);

        try {
            /*emailService.sendEmailWithAttachment(
                    email,
                    "Welcome Email",
                    "Your account has been created."
            );*/
            System.out.println("email sent!");

        } catch (RuntimeException e) {
            System.out.println("Email sending failed: " + e.getMessage());
            throw new EmailSendingException("Email sending failed!");
        }

        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
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
    @PutMapping("/{id}")
    public ResponseEntity<BankAccount> update(@PathVariable Long id, @RequestBody BankAccount bankAccount){
        BankAccount updateAccount = accountService.update(id, bankAccount);
        return new ResponseEntity<>(updateAccount, HttpStatus.OK);
    }
}
