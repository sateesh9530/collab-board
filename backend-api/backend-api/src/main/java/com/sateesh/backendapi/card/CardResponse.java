package com.sateesh.backendapi.card;

public record CardResponse(
        Long id,
        String title,
        String description,
        int position,
        Long listId
) {}
