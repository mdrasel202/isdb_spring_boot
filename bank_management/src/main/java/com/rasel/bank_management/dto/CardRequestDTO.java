package com.rasel.bank_management.dto;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.constants.CardType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDTO {
//    private Long bankAccountId;
    private String accountNumber;
    private CardType cardType;
    private CardStatus status;
    private Long cardId;
}
