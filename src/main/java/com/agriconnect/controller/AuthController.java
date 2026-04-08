package com.agriconnect.controller;

import com.agriconnect.dto.*;
import com.agriconnect.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Register, Login and current-user endpoints")
public class AuthController {

    private final AuthService authService;

    // ─── POST /api/auth/register ───────────────────────────────────────
    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Roles: admin | farmer | expert | public")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    // ─── POST /api/auth/login ─────────────────────────────────────────
    @PostMapping("/login")
    @Operation(summary = "Login with username + password", description = "Returns JWT token and user info")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    // ─── GET /api/auth/me ─────────────────────────────────────────────
    @GetMapping("/me")
    @Operation(summary = "Get current authenticated user's profile")
    public ResponseEntity<?> getMe(Authentication authentication) {
        UserDTO user = authService.getMe(authentication.getName());
        return ResponseEntity.ok(java.util.Map.of("user", user));
    }

    // ─── OAuth2 success redirect (optional) ──────────────────────────
    @GetMapping("/oauth2/success")
    @Operation(summary = "OAuth2 Google login success callback (optional)")
    public ResponseEntity<String> oauth2Success(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.ok("OAuth2 login not triggered.");
        }
        return ResponseEntity.ok("OAuth2 login successful for: " + authentication.getName());
    }
}
