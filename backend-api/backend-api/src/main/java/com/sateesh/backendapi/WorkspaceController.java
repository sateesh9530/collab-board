package com.sateesh.backendapi;

import com.sateesh.backendapi.workspace.WorkspaceRequest;
import com.sateesh.backendapi.workspace.WorkspaceResponse;
import com.sateesh.backendapi.workspace.WorkspaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspaces")
@RequiredArgsConstructor
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @PostMapping
    public ResponseEntity<WorkspaceResponse> create(@RequestBody WorkspaceRequest request) {
        WorkspaceResponse response = workspaceService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<WorkspaceResponse> getAll() {
        return workspaceService.getAll();
    }

    @GetMapping("/{id}")
    public WorkspaceResponse getById(@PathVariable Long id) {
        return workspaceService.getById(id);
    }
}
