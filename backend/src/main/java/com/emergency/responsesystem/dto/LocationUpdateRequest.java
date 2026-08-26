package com.emergency.responsesystem.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationUpdateRequest {

    @NotNull(message = "Latitude is required")
    private Double currentLat;

    @NotNull(message = "Longitude is required")
    private Double currentLng;
}
