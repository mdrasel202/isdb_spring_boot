package com.rasel.bank_management.service;

import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
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

//            if(bankAccount)

            if(bankAccount.getCurrency() != null){
                bankAccount1.setCurrency(bankAccount.getCurrency());
            }

            if(bankAccount.getStatus() != null){
                bankAccount1.setStatus(bankAccount.getStatus());
            }

            if(bankAccount.getBalance() != null){
                bankAccount1.setBalance(bankAccount.getBalance());
            }

            if(bankAccount.getAvailable_balance() != null){
                bankAccount1.setAvailable_balance(bankAccount.getAvailable_balance());
            }
            return accountRepository.save(bankAccount1);
        }else {
            throw new IllegalArgumentException("Account not found");
        }
    }
}
