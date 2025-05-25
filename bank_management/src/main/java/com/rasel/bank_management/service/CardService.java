package com.rasel.bank_management.service;

import com.rasel.bank_management.constants.CardStatus;
import com.rasel.bank_management.dto.CardGetAllDTO;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.rasel.bank_management.constants.CardStatus.APPROVED;
import static com.rasel.bank_management.constants.CardStatus.REJECTED;
import static com.rasel.bank_management.constants.CardType.VISA;

@Service
public class CardService {

    private final CardRepository cardRepository;
    private final AccountRepository accountRepository;

    public CardService(CardRepository cardRepository, AccountRepository accountRepository) {
        this.cardRepository = cardRepository;
        this.accountRepository = accountRepository;
    }


    public CardResponseDTO createCard(CardRequestDTO requestDTO) {
        BankAccount account = accountRepository.findById(requestDTO.getBankAccountId()).orElseThrow(() -> new RuntimeException("Bank account not found"));

        if (account.getAvailableBalance().compareTo(new BigDecimal("500")) < 0) {
            throw new RuntimeException("Insufficient balance to create carde");
        }

        account.setAvailableBalance(account.getAvailableBalance().subtract(new BigDecimal("500")));
        accountRepository.save(account);

        Card card = new Card();
        card.setId(card.getId());
        card.setCardNumber(generateUniqueCardNumber());
        card.setCard(requestDTO.getCardType());
        card.setStatus(CardStatus.REQUESTED);
        card.setExpiry_date(LocalDate.now().plusYears(4));
        card.setBankAccount(account);

        Card save = cardRepository.save(card);
////        cardRepo.save(card);
////        Card savedCard = cardRepository.save(card);
//
////        return mapToDto(savedCard);
//
        CardResponseDTO dto = new CardResponseDTO();
        dto.setCardNumber(card.getCardNumber());
        dto.setCardType(card.getCard());
        dto.setCardStatus(card.getStatus());
        dto.setExpiry_date(card.getExpiry_date());
        dto.setAccountId(account.getId());
        dto.setAvailableBalance(account.getAvailableBalance());

        return dto;
    }

    private String generateUniqueCardNumber() {

        return "CARD-" + UUID.randomUUID().toString().substring(0, 16);
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
    public List<CardGetAllDTO> getAllCard() {
        List<Card> allCards = cardRepository.findAll();
        List<CardGetAllDTO> dtoList = new ArrayList<>();

        for (Card card : allCards) {
            CardGetAllDTO dto = new CardGetAllDTO();
            dto.setId(card.getId());
            dto.setCardNumber(card.getCardNumber());
            dto.setCardType(card.getCard());
            dto.setStatus(card.getStatus());

            if (card.getBankAccount() != null) {
                dto.setOpenedDate(card.getBankAccount().getOpenedDate());
                dto.setAvailableBalance(card.getBankAccount().getBalance());
            }

            dtoList.add(dto);
        }

        return dtoList;
    }

//
////    @Transactional
////    public List<Card> getAllCard() {
////        List<Card> all = cardRepository.findAll();
////
////        for (Card card:all){
////            card.getBankAccount();
////        }
////        return all;
////    }
//
    /*public void rejectCard(CardRequestDTO dto) {
        Card card = cardRepository.findByBankAccountId(dto.getBankAccountId())
                .orElseThrow(() -> new RuntimeException("Card request not found"));
        card.setStatus(REJECTED);
        cardRepository.save(card);
    }*/
    public void rejectCard(CardRequestDTO dto) {
        Card card = cardRepository.findById(dto.getCardId())
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (!card.getBankAccount().getId().equals(dto.getBankAccountId())) {
            throw new RuntimeException("Card does not belong to the provided bank account.");
        }

        card.setStatus(CardStatus.REJECTED);
        cardRepository.save(card);
    }

    public CardResponseDTO approveCard(CardRequestDTO dto) {
        List<Card> cards = cardRepository.findAllByBankAccountId(dto.getBankAccountId());
        if (!cards.isEmpty()) {
            Card card = cards.getFirst();
            card.setCardNumber(generateUniqueCardNumber());
            card.setCard(VISA);
            card.setStatus(APPROVED);
            card.setExpiry_date(LocalDate.now().plusYears(3));
            cardRepository.save(card);

            return convertToDTO(card);
        } else {
            return null;
        }
    }

    public CardResponseDTO convertToDTO(Card card) {
        CardResponseDTO dto = new CardResponseDTO();
        dto.setCardNumber(card.getCardNumber());
        dto.setCardStatus(card.getStatus());
        dto.setExpiry_date(card.getExpiry_date());
        dto.setAvailableBalance(card.getBankAccount().getAvailableBalance());  // assuming you have this field
        dto.setId(card.getId());
        dto.setCardType(card.getCard());
        dto.setAccountId(card.getBankAccount().getId());
        return dto;
    }


    public CardResponseDTO updateCard(CardRequestDTO dto) {
        Card card = cardRepository.findById(dto.getCardId())
                .orElseThrow(() -> new RuntimeException("Card not found"));

        if (dto.getCardType() != null) {
            card.setCard(dto.getCardType());
        }

        if (dto.getStatus() != null) {
            card.setStatus(dto.getStatus());
        }

        cardRepository.save(card);
        return convertToDTO(card);
    }


    public List<CardResponseDTO> getPendingCards() {
        List<CardStatus> pendingStatuses = Arrays.asList(CardStatus.PENDING, CardStatus.REQUESTED);

        List<Card> pendingCards = cardRepository.findByStatusIn(pendingStatuses);
        return pendingCards.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CardResponseDTO mapToDto(Card card) {
        CardResponseDTO dto = new CardResponseDTO();
        dto.setId(card.getId());
        dto.setCardNumber(card.getCardNumber());
        dto.setCardType(card.getCard());
        dto.setCardStatus(card.getStatus());
        dto.setExpiry_date(card.getExpiry_date());
        dto.setAccountId(card.getBankAccount().getId());
        dto.setAvailableBalance(card.getBankAccount().getAvailableBalance());
        return dto;
    }

}
