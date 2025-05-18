package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.constants.CardType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CardGetAllDTO {
    private Long id;
    private String cardNumber;
    private CardType cardType;
    private CardStatus status;
    private LocalDate openedDate;
    private BigDecimal availableBalance;
}
