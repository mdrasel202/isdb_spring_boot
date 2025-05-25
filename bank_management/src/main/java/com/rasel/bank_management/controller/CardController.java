package com.rasel.bank_management.controller;

import com.rasel.bank_management.dto.CardGetAllDTO;
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
    public ResponseEntity<List<CardGetAllDTO>> getAll(){
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @PostMapping("/approve/{id}")
    public ResponseEntity<CardResponseDTO> approveCard(@RequestBody CardRequestDTO request) {
        return ResponseEntity.ok(cardService.approveCard(request)); // generates card number, status = "APPROVED"
    }

    @PostMapping("/reject")
    public ResponseEntity<Object> rejectCard(@RequestBody CardRequestDTO request) {
        cardService.rejectCard(request); // status = "REJECTED"
        Map<String, String> response = new HashMap<>();
        response.put("message", "Card request rejected.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<CardResponseDTO> updateCard(@RequestBody CardRequestDTO cardRequestDTO) {
        return ResponseEntity.ok(cardService.updateCard(cardRequestDTO));
    }

    @GetMapping("/pending")
    public ResponseEntity<List<CardResponseDTO>> getPendingCards() {
        return ResponseEntity.ok(cardService.getPendingCards());
    }
}
