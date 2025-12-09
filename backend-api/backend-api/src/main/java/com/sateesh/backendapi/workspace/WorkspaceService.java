package com.sateesh.backendapi.workspace;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    public WorkspaceResponse create(WorkspaceRequest request) {
        Workspace ws = Workspace.builder()
                .name(request.name())
                .description(request.description())
                .build();

        Workspace saved = workspaceRepository.save(ws);

        return new WorkspaceResponse(
                saved.getId(),
                saved.getName(),
                saved.getDescription(),
                saved.getCreatedAt()
        );
    }

    public List<WorkspaceResponse> getAll() {
        return workspaceRepository.findAll().stream()
                .map(w -> new WorkspaceResponse(
                        w.getId(),
                        w.getName(),
                        w.getDescription(),
                        w.getCreatedAt()
                ))
                .toList();
    }

    public WorkspaceResponse getById(Long id) {
        Workspace w = workspaceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workspace not found: " + id));

        return new WorkspaceResponse(
                w.getId(),
                w.getName(),
                w.getDescription(),
                w.getCreatedAt()
        );
    }
}
