package com.agriconnect.controller;

import com.agriconnect.dto.UserDTO;
import com.agriconnect.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "User profile management")
@SecurityRequirement(name = "BearerAuth")
public class UserController {

    private final UserService userService;

    // ─── GET /api/users/{id} ──────────────────────────────────────────
    @GetMapping("/{id}")
    @Operation(summary = "Get user profile by ID")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    // ─── PUT /api/users/{id} ──────────────────────────────────────────
    @PutMapping("/{id}")
    @Operation(summary = "Update user profile fields")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id,
                                              @RequestBody UserDTO dto) {
        return ResponseEntity.ok(userService.updateUser(id, dto));
    }

    // ─── DELETE /api/users/{id} ───────────────────────────────────────
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete own account")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "Account deleted successfully"));
    }
}
