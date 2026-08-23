package com.emergency.responsesystem.model;

import com.emergency.responsesystem.model.enums.ResponderStatus;
import com.emergency.responsesystem.model.enums.ResponderType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "responders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Responder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ResponderType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ResponderStatus status = ResponderStatus.OFFLINE;

    @Column(nullable = false)
    private Double currentLat;

    @Column(nullable = false)
    private Double currentLng;

    @Column(nullable = false)
    private LocalDateTime lastLocationUpdate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.lastLocationUpdate = LocalDateTime.now();
    }
}
