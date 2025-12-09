package com.sateesh.backendapi;

import com.sateesh.backendapi.boardlist.BoardListRequest;
import com.sateesh.backendapi.boardlist.BoardListResponse;
import com.sateesh.backendapi.boardlist.BoardListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardListController {

    private final BoardListService boardListService;

    @PostMapping("/api/boards/{boardId}/lists")
    public ResponseEntity<BoardListResponse> create(
            @PathVariable Long boardId,
            @RequestBody BoardListRequest request
    ) {
        BoardListResponse response = boardListService.create(boardId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/boards/{boardId}/lists")
    public List<BoardListResponse> getByBoard(@PathVariable Long boardId) {
        return boardListService.getByBoard(boardId);
    }

    @GetMapping("/api/lists/{listId}")
    public BoardListResponse getById(@PathVariable Long listId) {
        return boardListService.getById(listId);
    }
}
