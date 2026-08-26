package com.emergency.responsesystem.dto;

import com.emergency.responsesystem.model.enums.ResponderStatus;
import com.emergency.responsesystem.model.enums.ResponderType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponderResponse {
    private Long id;
    private String name;
    private String email;
    private ResponderType type;
    private ResponderStatus status;
    private Double currentLat;
    private Double currentLng;
    private LocalDateTime lastLocationUpdate;
}
