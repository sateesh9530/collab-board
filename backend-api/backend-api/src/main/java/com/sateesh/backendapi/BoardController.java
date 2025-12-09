package com.sateesh.backendapi;

import com.sateesh.backendapi.board.BoardRequest;
import com.sateesh.backendapi.board.BoardResponse;
import com.sateesh.backendapi.board.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/workspaces/{workspaceId}/boards")
    public ResponseEntity<BoardResponse> create(
            @PathVariable Long workspaceId,
            @RequestBody BoardRequest request
    ) {
        BoardResponse response = boardService.create(workspaceId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/api/workspaces/{workspaceId}/boards")
    public List<BoardResponse> getByWorkspace(@PathVariable Long workspaceId) {
        return boardService.getByWorkspace(workspaceId);
    }

    @GetMapping("/api/boards/{boardId}")
    public BoardResponse getById(@PathVariable Long boardId) {
        return boardService.getById(boardId);
    }
}
