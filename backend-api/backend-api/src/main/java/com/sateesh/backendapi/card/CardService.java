package com.sateesh.backendapi.card;

import com.sateesh.backendapi.boardlist.BoardList;
import com.sateesh.backendapi.boardlist.BoardListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final BoardListRepository boardListRepository;

    public CardResponse create(Long listId, CardRequest request) {
        BoardList list = boardListRepository.findById(listId)
                .orElseThrow(() -> new RuntimeException("List not found: " + listId));

        Card card = Card.builder()
                .title(request.title())
                .description(request.description())
                .position(request.position())
                .list(list)
                .build();

        Card saved = cardRepository.save(card);
        return toResponse(saved);
    }

    public List<CardResponse> getByList(Long listId) {
        return cardRepository.findByListIdOrderByPositionAsc(listId).stream()
                .map(this::toResponse)
                .toList();
    }

    public CardResponse getById(Long id) {
        Card card = cardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Card not found: " + id));
        return toResponse(card);
    }

    private CardResponse toResponse(Card c) {
        return new CardResponse(
                c.getId(),
                c.getTitle(),
                c.getDescription(),
                c.getPosition(),
                c.getList().getId()
        );
    }
}
