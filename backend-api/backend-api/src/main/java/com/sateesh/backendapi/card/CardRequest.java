package com.sateesh.backendapi.card;

public record CardRequest(
        String title,
        String description,
        int position
) {}
