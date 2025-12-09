package com.sateesh.backendapi.board;

import java.time.Instant;

public record BoardResponse(Long id,
                            String name,
                            String description,
                            Long workspaceId,
                            Instant createdAt) {
}
