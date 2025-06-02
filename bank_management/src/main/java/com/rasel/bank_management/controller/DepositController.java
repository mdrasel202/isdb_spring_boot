package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.DepositResponseDTO;
import com.rasel.bank_management.dto.DepostRequestDTO;
import com.rasel.bank_management.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/deposit")
public class DepositController {

    private final DepositService depositService;

    public DepositController(DepositService depositService){
        this.depositService = depositService;
    }

    @PostMapping("/response")
    public ResponseEntity<DepositResponseDTO> request(@RequestBody DepostRequestDTO depostRequestDTO){
        DepositResponseDTO response = depositService.request(depostRequestDTO);
        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }
}
