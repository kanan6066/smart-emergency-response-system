package com.emergency.responsesystem.repository;

import com.emergency.responsesystem.model.Incident;
import com.emergency.responsesystem.model.enums.IncidentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IncidentRepository extends JpaRepository<Incident, Long> {
    List<Incident> findByStatus(IncidentStatus status);
    List<Incident> findByReporterId(Long reporterId);
}
