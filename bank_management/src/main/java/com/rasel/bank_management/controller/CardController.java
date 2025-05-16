package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.CardRequestDTO;
import com.rasel.bank_management.dto.CardResponseDTO;
import com.rasel.bank_management.model.Card;
import com.rasel.bank_management.service.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/cards")
public class CardController {

    private final CardService cardService;

    public CardController(CardService cardService){
        this.cardService = cardService;
    }

    @PostMapping("/request")
    public ResponseEntity<Object> userRequest(@RequestBody CardRequestDTO cardRequestDTO){
        cardService.saveCard(cardRequestDTO);
//        return ResponseEntity.ok("Card request submitted.");
        Map<String, String> response = new HashMap<>();
        response.put("message", "Card request submitted.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<CardResponseDTO> createCard(@RequestBody CardRequestDTO cardRequestDTO){
        return ResponseEntity.ok(cardService.createCard(cardRequestDTO));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Card>> getAll(){
        return ResponseEntity.ok(cardService.getAllCard());
    }
}
