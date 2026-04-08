package com.agriconnect.controller;

import com.agriconnect.dto.AdminStatsDTO;
import com.agriconnect.dto.UserDTO;
import com.agriconnect.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@Tag(name = "Admin", description = "Admin-only user management and statistics")
@SecurityRequirement(name = "BearerAuth")
public class AdminController {

    private final AdminService adminService;

    // GET ALL USERS
    @GetMapping("/users")
    @Operation(summary = "Get all registered users (admin only)")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(adminService.getAllUsers());
    }
 // CREATE USER
    @PostMapping("/users")
    @Operation(summary = "Create a new user (admin only)")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = adminService.createUser(userDTO);
        return ResponseEntity.ok(createdUser);
    }

    // DELETE USER
    @DeleteMapping("/users/{id}")
    @Operation(summary = "Delete a user by ID (admin only)")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable Long id) {
        adminService.deleteUser(id);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }

    // ⭐ FIX: UPDATE USER (THIS WAS MISSING)
    @PutMapping("/users/{id}")
    @Operation(summary = "Update user by ID (admin only)")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserDTO userDTO
    ) {
        UserDTO updatedUser = adminService.updateUser(id, userDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // GET STATS
    @GetMapping("/stats")
    @Operation(summary = "Get user statistics dashboard (admin only)")
    public ResponseEntity<AdminStatsDTO> getStats() {
        return ResponseEntity.ok(adminService.getStats());
    }
}