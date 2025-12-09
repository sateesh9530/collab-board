package com.sateesh.backendapi;

import com.sateesh.backendapi.card.CardRequest;
import com.sateesh.backendapi.card.CardResponse;
import com.sateesh.backendapi.card.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/api/lists/{listId}/cards")
    public ResponseEntity<CardResponse> create(
            @PathVariable Long listId,
            @RequestBody CardRequest request
    ) {
        CardResponse response = cardService.create(listId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/lists/{listId}/cards")
    public List<CardResponse> getByList(@PathVariable Long listId) {
        return cardService.getByList(listId);
    }

    @GetMapping("/api/cards/{cardId}")
    public CardResponse getById(@PathVariable Long cardId) {
        return cardService.getById(cardId);
    }
}
