package com.agriconnect.controller;

import com.agriconnect.entity.Session;
import com.agriconnect.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class SessionController {

    private final SessionService sessionService;

    // ✅ CREATE SESSION
    @PostMapping
    public Session createSession(@RequestBody Session session,
                                 @RequestParam String username) {
        return sessionService.createSession(session, username);
    }

    // ✅ ALL SESSIONS
    @GetMapping
    public List<Session> getAllSessions() {
        return sessionService.getAllSessions();
    }

    // ✅ MY SESSIONS
    @GetMapping("/my")
    public List<Session> getMySessions(@RequestParam String username) {
        return sessionService.getMySessions(username);
    }

    // ✅ REGISTER
    @PostMapping("/register/{id}")
    public String register(@PathVariable Long id,
                           @RequestParam String username) {
        return sessionService.register(id, username);
    }

    // ✅ UNREGISTER
    @PostMapping("/unregister/{id}")
    public String unregister(@PathVariable Long id,
                             @RequestParam String username) {
        return sessionService.unregister(id, username);
    }
    
}