package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.AccountStatus;
import com.rasel.bank_management.constants.AccountType;
import com.rasel.bank_management.constants.Role;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountRequest{
    public String getEmail;
    private String accountNumber;
    private BigDecimal availableBalance;
    private LocalDate openedDate;
    private String address;
    private String phone;
    private LocalDate birthDay;
    private AccountStatus accountStatus;
    private AccountType accountType;
    private String firstName;
    private String lastName;
}
