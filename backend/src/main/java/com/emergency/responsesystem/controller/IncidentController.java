package com.emergency.responsesystem.controller;

import com.emergency.responsesystem.dto.IncidentRequest;
import com.emergency.responsesystem.dto.IncidentResponse;
import com.emergency.responsesystem.dto.IncidentStatusUpdateRequest;
import com.emergency.responsesystem.model.User;
import com.emergency.responsesystem.model.enums.IncidentStatus;
import com.emergency.responsesystem.service.IncidentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping
    public ResponseEntity<IncidentResponse> createIncident(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody IncidentRequest request
    ) {
        return ResponseEntity.ok(incidentService.createIncident(currentUser, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentResponse> getIncidentById(@PathVariable Long id) {
        return ResponseEntity.ok(incidentService.getIncidentById(id));
    }

    @GetMapping
    public ResponseEntity<List<IncidentResponse>> getAllIncidents() {
        return ResponseEntity.ok(incidentService.getAllIncidents());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<IncidentResponse>> getIncidentsByStatus(@PathVariable IncidentStatus status) {
        return ResponseEntity.ok(incidentService.getIncidentsByStatus(status));
    }

    @GetMapping("/my")
    public ResponseEntity<List<IncidentResponse>> getMyIncidents(@AuthenticationPrincipal User currentUser) {
        return ResponseEntity.ok(incidentService.getMyIncidents(currentUser));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<IncidentResponse> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody IncidentStatusUpdateRequest request
    ) {
        return ResponseEntity.ok(incidentService.updateStatus(id, request));
    }
}
