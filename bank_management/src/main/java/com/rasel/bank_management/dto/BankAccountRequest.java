package com.rasel.bank_management.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountRequest {
    private Integer id;
    private String name;
    private String email;
    private String phone;
    private String nid;
    private LocalDate birthdayDate;
    private String address;

}
