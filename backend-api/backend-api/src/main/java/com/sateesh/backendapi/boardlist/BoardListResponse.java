package com.sateesh.backendapi.boardlist;

public record BoardListResponse(
        Long id,
        String title,
        int position,
        Long boardId
) {
}
