package com.agriconnect.repository;

import com.agriconnect.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRepository extends JpaRepository<Session, Long> {

    // ✅ FETCH USER CREATED SESSIONS
    List<Session> findByCreatedBy(String createdBy);
}