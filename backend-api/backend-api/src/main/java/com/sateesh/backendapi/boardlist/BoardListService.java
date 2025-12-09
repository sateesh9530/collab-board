package com.sateesh.backendapi.boardlist;

import com.sateesh.backendapi.board.Board;
import com.sateesh.backendapi.board.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardListService {

    private final BoardListRepository boardListRepository;
    private final BoardRepository boardRepository;

    public BoardListResponse create(Long boardId, BoardListRequest request) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found: " + boardId));

        BoardList list = BoardList.builder()
                .title(request.title())
                .position(request.position())
                .board(board)
                .build();

        BoardList saved = boardListRepository.save(list);
        return toResponse(saved);
    }

    public List<BoardListResponse> getByBoard(Long boardId) {
        return boardListRepository.findByBoardIdOrderByPositionAsc(boardId).stream()
                .map(this::toResponse)
                .toList();
    }

    public BoardListResponse getById(Long id) {
        BoardList list = boardListRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("List not found: " + id));
        return toResponse(list);
    }

    private BoardListResponse toResponse(BoardList list) {
        return new BoardListResponse(
                list.getId(),
                list.getTitle(),
                list.getPosition(),
                list.getBoard().getId()
        );
    }
}
