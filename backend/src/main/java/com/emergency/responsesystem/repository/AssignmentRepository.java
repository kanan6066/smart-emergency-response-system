package com.emergency.responsesystem.repository;

import com.emergency.responsesystem.model.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
    Optional<Assignment> findByIncidentId(Long incidentId);
}
