package com.rasel.bank_management.service;

import com.rasel.bank_management.dto.BankAccountResponseDTO;
import com.rasel.bank_management.dto.BankDepositRequestDTO;
import com.rasel.bank_management.repository.BankDepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankDepositService {

    @Autowired
    private BankDepositRepository bankDepositRepository;

    //Bank Deposit Request
    public BankAccountResponseDTO requests(BankDepositRequestDTO requestDTO) {
        return null;
    }
}
