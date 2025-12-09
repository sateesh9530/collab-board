package com.sateesh.backendapi.board;

import com.sateesh.backendapi.workspace.Workspace;
import com.sateesh.backendapi.workspace.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final WorkspaceRepository workspaceRepository;

    public BoardResponse create(Long workspaceId, BoardRequest request) {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new RuntimeException("Workspace not found: " + workspaceId));

        Board board = Board.builder()
                .name(request.name())
                .description(request.description())
                .workspace(workspace)
                .build();

        Board saved = boardRepository.save(board);

        return toResponse(saved);
    }

    public List<BoardResponse> getByWorkspace(Long workspaceId) {
        return boardRepository.findByWorkspaceId(workspaceId).stream()
                .map(this::toResponse)
                .toList();
    }

    public BoardResponse getById(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Board not found: " + id));

        return toResponse(board);
    }

    private BoardResponse toResponse(Board b) {
        return new BoardResponse(
                b.getId(),
                b.getName(),
                b.getDescription(),
                b.getWorkspace().getId(),
                b.getCreatedAt()
        );
    }
}
