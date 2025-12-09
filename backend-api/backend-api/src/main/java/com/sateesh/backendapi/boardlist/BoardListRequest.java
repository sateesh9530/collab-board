package com.sateesh.backendapi.boardlist;

public record BoardListRequest(
        String title,
        int position
) {}
