package com.emergency.responsesystem.repository;

import com.emergency.responsesystem.model.Responder;
import com.emergency.responsesystem.model.enums.ResponderStatus;
import com.emergency.responsesystem.model.enums.ResponderType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResponderRepository extends JpaRepository<Responder, Long> {
    List<Responder> findByStatusAndType(ResponderStatus status, ResponderType type);
    Optional<Responder> findByUserId(Long userId);
}
