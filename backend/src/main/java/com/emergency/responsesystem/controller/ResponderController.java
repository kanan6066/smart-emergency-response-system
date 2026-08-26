package com.emergency.responsesystem.controller;

import com.emergency.responsesystem.dto.LocationUpdateRequest;
import com.emergency.responsesystem.dto.ResponderRequest;
import com.emergency.responsesystem.dto.ResponderResponse;
import com.emergency.responsesystem.dto.StatusUpdateRequest;
import com.emergency.responsesystem.model.User;
import com.emergency.responsesystem.model.enums.ResponderType;
import com.emergency.responsesystem.service.ResponderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responders")
@RequiredArgsConstructor
public class ResponderController {

    private final ResponderService responderService;

    @PostMapping("/profile")
    public ResponseEntity<ResponderResponse> createProfile(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody ResponderRequest request
    ) {
        return ResponseEntity.ok(responderService.createResponderProfile(currentUser, request));
    }

    @PutMapping("/location")
    public ResponseEntity<ResponderResponse> updateLocation(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody LocationUpdateRequest request
    ) {
        return ResponseEntity.ok(responderService.updateLocation(currentUser, request));
    }

    @PutMapping("/status")
    public ResponseEntity<ResponderResponse> updateStatus(
            @AuthenticationPrincipal User currentUser,
            @Valid @RequestBody StatusUpdateRequest request
    ) {
        return ResponseEntity.ok(responderService.updateStatus(currentUser, request));
    }

    @GetMapping("/available")
    public ResponseEntity<List<ResponderResponse>> getAvailableResponders(
            @RequestParam ResponderType type
    ) {
        return ResponseEntity.ok(responderService.getAvailableResponders(type));
    }

    @GetMapping
    public ResponseEntity<List<ResponderResponse>> getAllResponders() {
        return ResponseEntity.ok(responderService.getAllResponders());
    }
}
