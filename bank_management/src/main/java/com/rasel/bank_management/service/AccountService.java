package com.rasel.bank_management.service;

import com.rasel.bank_management.constants.AccountStatus;
import com.rasel.bank_management.dto.BankAccountRequestDTO;
import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.User;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.UserRepository;
import org.springframework.stereotype.Service;

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
    public AccountService(AccountRepository accountRepository, UserRepository userRepository){
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    public void requestBankAccount(BankAccountRequestDTO dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        BankAccount account = new BankAccount();
        account.setAccountNumber(generateAccountNumber());
        account.setUser(user);
        account.setType(dto.getType());
        account.setStatus(AccountStatus.REQUESTED);
        account.setAvailableBalance(BigDecimal.ZERO);
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
        account.setAvailableBalance(BigDecimal.valueOf(100));
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
}
