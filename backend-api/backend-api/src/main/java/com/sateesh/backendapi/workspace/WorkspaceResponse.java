package com.sateesh.backendapi.workspace;

import java.time.Instant;

public record WorkspaceResponse(Long id,
                                String name,
                                String description,
                                Instant createdAt) {
}
