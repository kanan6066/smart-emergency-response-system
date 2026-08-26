package com.emergency.responsesystem.service;

import com.emergency.responsesystem.dto.LocationUpdateRequest;
import com.emergency.responsesystem.dto.ResponderRequest;
import com.emergency.responsesystem.dto.ResponderResponse;
import com.emergency.responsesystem.dto.StatusUpdateRequest;
import com.emergency.responsesystem.model.Responder;
import com.emergency.responsesystem.model.User;
import com.emergency.responsesystem.model.enums.ResponderStatus;
import com.emergency.responsesystem.model.enums.ResponderType;
import com.emergency.responsesystem.repository.ResponderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResponderService {

    private final ResponderRepository responderRepository;

    public ResponderResponse createResponderProfile(User currentUser, ResponderRequest request) {
        if (responderRepository.findByUserId(currentUser.getId()).isPresent()) {
            throw new IllegalArgumentException("Responder profile already exists for this user");
        }

        Responder responder = Responder.builder()
                .user(currentUser)
                .type(request.getType())
                .status(ResponderStatus.OFFLINE)
                .currentLat(request.getCurrentLat())
                .currentLng(request.getCurrentLng())
                .build();

        Responder saved = responderRepository.save(responder);
        return toResponse(saved);
    }

    public ResponderResponse updateLocation(User currentUser, LocationUpdateRequest request) {
        Responder responder = responderRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Responder profile not found"));

        responder.setCurrentLat(request.getCurrentLat());
        responder.setCurrentLng(request.getCurrentLng());
        responder.setLastLocationUpdate(LocalDateTime.now());

        Responder saved = responderRepository.save(responder);
        return toResponse(saved);
    }

    public ResponderResponse updateStatus(User currentUser, StatusUpdateRequest request) {
        Responder responder = responderRepository.findByUserId(currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Responder profile not found"));

        responder.setStatus(request.getStatus());

        Responder saved = responderRepository.save(responder);
        return toResponse(saved);
    }

    public List<ResponderResponse> getAvailableResponders(ResponderType type) {
        return responderRepository.findByStatusAndType(ResponderStatus.AVAILABLE, type)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public List<ResponderResponse> getAllResponders() {
        return responderRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ResponderResponse toResponse(Responder responder) {
        return ResponderResponse.builder()
                .id(responder.getId())
                .name(responder.getUser().getName())
                .email(responder.getUser().getEmail())
                .type(responder.getType())
                .status(responder.getStatus())
                .currentLat(responder.getCurrentLat())
                .currentLng(responder.getCurrentLng())
                .lastLocationUpdate(responder.getLastLocationUpdate())
                .build();
    }
}
