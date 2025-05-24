package com.rasel.bank_management.service;

import com.rasel.bank_management.constants.AccountStatus;
import com.rasel.bank_management.dto.BankAccountRequestDTO;
import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.dto.TransferRequestDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.Transaction;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.TransactionRepository;
import com.rasel.bank_management.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;

    public AccountService(AccountRepository accountRepository, UserRepository userRepository, TransactionRepository transactionRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    public void requestBankAccount(BankAccountRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setAccountNumber(generateAccountNumber());
        account.setUser(user);
        account.setType(dto.getType());
        account.setStatus(AccountStatus.REQUESTED);
        // Set balance to DTO value if present, else zero
        if (dto.getBalance() != null) {
            account.setBalance(dto.getBalance());
        } else {
            account.setBalance(BigDecimal.ZERO);
        }
        account.setAvailableBalance(dto.getBalance());
        account.setOpenedDate(LocalDate.now());
        account.setName(dto.getName());
        account.setRequestDate(LocalDate.now());
        account.setOpenedDate(LocalDate.now());

        accountRepository.save(account);
    }

    public List<BankAccountResponseDTO> getAllRequestedAccounts() {
        return accountRepository.findByStatus(AccountStatus.REQUESTED)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public BankAccountResponseDTO approveAccount(Long accountId) {
        BankAccount account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setStatus(AccountStatus.ACTIVE);
        account.setAvailableBalance(account.getAvailableBalance());
        account.setOpenedDate(LocalDate.now());
        accountRepository.save(account);

        return mapToDto(account);
    }

    private BankAccountResponseDTO mapToDto(BankAccount account) {
        BankAccountResponseDTO dto = new BankAccountResponseDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setUserId(account.getUser().getId());
        dto.setUserName(account.getUser().getFirstName() + " " + account.getUser().getLastName());
        dto.setType(account.getType());
        dto.setStatus(account.getStatus());
        dto.setAvailableBalance(account.getAvailableBalance());
        dto.setOpenedDate(account.getOpenedDate());
        return dto;
    }

    private String generateAccountNumber() {
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0, 12);
    }

    //Transation
    @Transactional
    public void transferBalance(TransferRequestDTO request) {
        BankAccount fromAccount = accountRepository.findById(request.getFromAccountId())
                .orElseThrow(() -> new RuntimeException("Sender account not found"));
        BankAccount toAccount = accountRepository.findById(request.getToAccountId())
                .orElseThrow(() -> new RuntimeException("Receiver account not found"));

        BigDecimal amount = request.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Transfer amount must be greater than zero");
        }

        if (fromAccount.getAvailableBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Insufficient balance in sender's account");
        }

        // Update balances
        fromAccount.setAvailableBalance(fromAccount.getAvailableBalance().subtract(amount));
        toAccount.setAvailableBalance(toAccount.getAvailableBalance().add(amount));

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        // Save transaction for sender
        Transaction debit = new Transaction();
        debit.setBankAccount(fromAccount);
        debit.setAmount(amount.negate());
        debit.setDescription("Transfer to Account #" + toAccount.getAccountNumber());
        debit.setDate(LocalDate.now());
        transactionRepository.save(debit);

        // Save transaction for receiver
        Transaction credit = new Transaction();
        credit.setBankAccount(toAccount);
        credit.setAmount(amount);
        credit.setDescription("Transfer from Account #" + fromAccount.getAccountNumber());
        credit.setDate(LocalDate.now());
        transactionRepository.save(credit);
    }

    public List<Transaction> getTransactionsForAccount(Long accountId) {
        return transactionRepository.findByBankAccountId(accountId);
    }

//    public List<BankAccountResponseDTO> findAllAccount() {
//        return accountRepository.findAll();
//    }


    //get all
    public List<BankAccountResponseDTO> findAllAccount() {
        List<BankAccount> accounts = accountRepository.findAll();
        return accounts.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private BankAccountResponseDTO mapToDTO(BankAccount account) {
        BankAccountResponseDTO dto = new BankAccountResponseDTO();
        dto.setId(account.getId());
        dto.setAccountNumber(account.getAccountNumber());
        dto.setUserId(account.getUser().getId());
        dto.setUserName(account.getUser().getFirstName() + " " + account.getUser().getLastName());
        dto.setType(account.getType());
        dto.setStatus(account.getStatus());
        dto.setAvailableBalance(account.getAvailableBalance());
        dto.setOpenedDate(account.getOpenedDate());
        return dto;
    }

}
