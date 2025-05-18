package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.constants.CardType;
import com.rasel.bank_management.model.Card;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CardResponseDTO {
    private String cardNumber;
    private CardType cardType;
    private CardStatus cardStatus;
    private LocalDate expiry_date;

    private Long accountId;
    private BigDecimal availableBalance;



}
