package com.emergency.responsesystem.dto;

import com.emergency.responsesystem.model.enums.IncidentStatus;
import com.emergency.responsesystem.model.enums.ResponderType;
import com.emergency.responsesystem.model.enums.Severity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentResponse {
    private Long id;
    private String reporterName;
    private String description;
    private ResponderType requiredResponderType;
    private Severity severity;
    private IncidentStatus status;
    private Double lat;
    private Double lng;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}
