package com.agriconnect.service;

import com.agriconnect.entity.Session;
import com.agriconnect.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    // ✅ CREATE SESSION
    public Session createSession(Session session, String username) {
        session.setCreatedBy(username);
        return sessionRepository.save(session);
    }

    // ✅ GET ALL SESSIONS
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    // ✅ GET ONLY USER SESSIONS
    public List<Session> getMySessions(String username) {
        return sessionRepository.findByCreatedBy(username);
    }

    // ✅ REGISTER
    public String register(Long sessionId, String username) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (session.getDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Cannot register for past session");
        }

        if (session.getRegisteredUsers().contains(username)) {
            return "Already registered";
        }

        session.getRegisteredUsers().add(username);
        sessionRepository.save(session);

        return "Registered Successfully";
    }

    // ✅ UNREGISTER
    public String unregister(Long sessionId, String username) {

        Session session = sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));

        if (!session.getRegisteredUsers().contains(username)) {
            throw new RuntimeException("User not registered");
        }

        session.getRegisteredUsers().remove(username);
        sessionRepository.save(session);

        return "Unregistered Successfully";
    }
    
}