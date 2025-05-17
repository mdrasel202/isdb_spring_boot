package com.rasel.bank_management.service;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.dto.CardRequestDTO;
import com.rasel.bank_management.dto.CardResponseDTO;
import com.rasel.bank_management.model.BankAccount;
import com.rasel.bank_management.model.Card;
import com.rasel.bank_management.repository.AccountRepository;
import com.rasel.bank_management.repository.CardRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository, AccountRepository accountRepository){
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }

    public CardResponseDTO createCard(CardRequestDTO requestDTO){
        BankAccount account = accountRepository.findById(requestDTO.getBankAccountId()).orElseThrow(() -> new RuntimeException("Bank account not found"));

        if(account.getAvailableBalance().compareTo(new BigDecimal("5.00")) < 0){
            throw  new RuntimeException("Insufficient balance to create carde");
        }

        account.setAvailableBalance(account.getAvailableBalance().subtract(new BigDecimal("5.00")));
        accountRepository.save(account);

        Card card = new Card();
        card.setCardNumber(generateUniqueCardNumber());
        card.setCard(requestDTO.getCardType());
        card.setStatus(CardStatus.REQUESTED);
        card.setExpiry_date(LocalDate.now().plusYears(4));
        card.setBankAccount(account);

        Card save = cardRepository.save(card);
//        cardRepo.save(card);
//        Card savedCard = cardRepository.save(card);

//        return mapToDto(savedCard);

        CardResponseDTO dto = new CardResponseDTO();
        dto.setCardNumber(card.getCardNumber());
        dto.setCardType(card.getCard());
        dto.setCardStatus(card.getStatus());
        dto.setExpiry_date(card.getExpiry_date());
        dto.setAccountId(account.getId());
        dto.setAvailableBalance(account.getAvailableBalance());

        return dto;
    }

    private String generateUniqueCardNumber(){
        return String.valueOf((long)(Math.random() * 1_0000_0000_0000_0000L));
    }

    public void saveCard(CardRequestDTO cardRequestDTO) {
        BankAccount account = accountRepository.findById(cardRequestDTO.getBankAccountId()).orElseThrow(() -> new RuntimeException("Account not found"));

        Card card = new Card();
        card.setCardNumber(generateUniqueCardNumber());
        card.setCard(cardRequestDTO.getCardType());
        card.setStatus(CardStatus.REQUESTED);
        card.setExpiry_date(LocalDate.now().plusYears(4));
        card.setBankAccount(account);

        cardRepository.save(card);
    }

    @Transactional
    public List<Card> getAllCard() {
        List<Card> all = cardRepository.findAll();

        for (Card card:all){
            card.getBankAccount();
        }
        return all;
    }
}
