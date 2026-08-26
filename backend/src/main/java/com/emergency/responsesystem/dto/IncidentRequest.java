package com.emergency.responsesystem.dto;

import com.emergency.responsesystem.model.enums.ResponderType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IncidentRequest {

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Required responder type is required")
    private ResponderType requiredResponderType;

    @NotNull(message = "Latitude is required")
    private Double lat;

    @NotNull(message = "Longitude is required")
    private Double lng;

    @NotBlank(message = "Address is required")
    private String address;
}
