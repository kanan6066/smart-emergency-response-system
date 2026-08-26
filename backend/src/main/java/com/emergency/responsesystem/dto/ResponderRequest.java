package com.emergency.responsesystem.dto;

import com.emergency.responsesystem.model.enums.ResponderType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponderRequest {

    @NotNull(message = "Responder type is required")
    private ResponderType type;

    @NotNull(message = "Latitude is required")
    private Double currentLat;

    @NotNull(message = "Longitude is required")
    private Double currentLng;
}
