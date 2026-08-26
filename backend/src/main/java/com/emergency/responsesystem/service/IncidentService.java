package com.emergency.responsesystem.service;

import com.emergency.responsesystem.dto.IncidentRequest;
import com.emergency.responsesystem.dto.IncidentResponse;
import com.emergency.responsesystem.dto.IncidentStatusUpdateRequest;
import com.emergency.responsesystem.model.Incident;
import com.emergency.responsesystem.model.User;
import com.emergency.responsesystem.model.enums.IncidentStatus;
import com.emergency.responsesystem.model.enums.Severity;
import com.emergency.responsesystem.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncidentService {

    private final IncidentRepository incidentRepository;

    public IncidentResponse createIncident(User reporter, IncidentRequest request) {
        Incident incident = Incident.builder()
                .reporter(reporter)
                .description(request.getDescription())
                .requiredResponderType(request.getRequiredResponderType())
                .severity(classifySeverity(request.getDescription()))
                .status(IncidentStatus.PENDING)
                .lat(request.getLat())
                .lng(request.getLng())
                .address(request.getAddress())
                .build();

        Incident saved = incidentRepository.save(incident);
        return toResponse(saved);
    }

    public IncidentResponse getIncidentById(Long id) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incident not found"));
        return toResponse(incident);
    }

    public List<IncidentResponse> getAllIncidents() {
        return incidentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<IncidentResponse> getIncidentsByStatus(IncidentStatus status) {
        return incidentRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<IncidentResponse> getMyIncidents(User reporter) {
        return incidentRepository.findByReporterId(reporter.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public IncidentResponse updateStatus(Long id, IncidentStatusUpdateRequest request) {
        Incident incident = incidentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Incident not found"));

        incident.setStatus(request.getStatus());

        if (request.getStatus() == IncidentStatus.RESOLVED) {
            incident.setResolvedAt(LocalDateTime.now());
        }

        Incident saved = incidentRepository.save(incident);
        return toResponse(saved);
    }

    // Temporary basic rule-based classifier - will be replaced/expanded in Phase 5
    private Severity classifySeverity(String description) {
        String desc = description.toLowerCase();
        if (desc.contains("fire") || desc.contains("unconscious") || desc.contains("gun") || desc.contains("critical")) {
            return Severity.CRITICAL;
        } else if (desc.contains("injury") || desc.contains("accident") || desc.contains("smoke")) {
            return Severity.MEDIUM;
        }
        return Severity.LOW;
    }

    private IncidentResponse toResponse(Incident incident) {
        return IncidentResponse.builder()
                .id(incident.getId())
                .reporterName(incident.getReporter().getName())
                .description(incident.getDescription())
                .requiredResponderType(incident.getRequiredResponderType())
                .severity(incident.getSeverity())
                .status(incident.getStatus())
                .lat(incident.getLat())
                .lng(incident.getLng())
                .address(incident.getAddress())
                .createdAt(incident.getCreatedAt())
                .resolvedAt(incident.getResolvedAt())
                .build();
    }
}
