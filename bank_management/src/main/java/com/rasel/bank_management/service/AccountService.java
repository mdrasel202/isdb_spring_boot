package com.rasel.bank_management.service;

import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    //get id
    public BankAccount getId(Long id) {
        BankAccount get = accountRepository.findById(id).orElse(null);
        return get;
    }
    //post
    public BankAccount saveAccount(BankAccount bankAccount) {
        BankAccount save = accountRepository.save(bankAccount);
        return save;
    }
    //get all
    public List<BankAccount> getAll() {
        List<BankAccount> getall = accountRepository.findAll();
        return getall;
    }
    //delete
    public void deleteId(Long id) {
        accountRepository.deleteById(id);
    }
    //put
    public BankAccount update(Long id, BankAccount bankAccount) {
        Optional<BankAccount> bankAccountById = accountRepository.findById(id);

        if(bankAccountById.isPresent()){
            BankAccount bankAccount1 = new BankAccount();
            if(bankAccount.getAccountNumber() != null){
                bankAccount1.setAccountNumber(bankAccount.getAccountNumber());
            }

            if(bankAccount.getType() != null){
                bankAccount1.setType(bankAccount.getType());
            }

           if(bankAccount.getUser() != null && bankAccount.getUser().getId() != null){
               Integer userId = bankAccount.getUser().getId();
               User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
               bankAccount1.setUser(user);
           }

            if(bankAccount.getStatus() != null){
                bankAccount1.setStatus(bankAccount.getStatus());
            }

            if(bankAccount.getAvailableBalance() != null){
                bankAccount1.setAvailableBalance(bankAccount.getAvailableBalance());
            }

            if(bankAccount.getOpenedDate() != null){
                bankAccount1.setOpenedDate(bankAccount.getOpenedDate());
            }
            return accountRepository.save(bankAccount1);
        }else {
            throw new IllegalArgumentException("Account not found");
        }
    }
}
