package com.agriconnect.controller;

import com.agriconnect.dto.GuidanceDTO;
import com.agriconnect.service.GuidanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/guidance")
@RequiredArgsConstructor
@Tag(name = "Guidance", description = "Submit and view agricultural guidance requests")
public class GuidanceController {

    private final GuidanceService guidanceService;

    // ─── POST /api/guidance ──────────────────────────────────────────
    @PostMapping
    @Operation(summary = "Submit a guidance request (open — no auth required)")
    public ResponseEntity<GuidanceDTO> submitGuidance(@Valid @RequestBody GuidanceDTO dto) {
        return ResponseEntity.ok(guidanceService.submitGuidance(dto));
    }

    // ─── GET /api/guidance ───────────────────────────────────────────
    @GetMapping
    @Operation(summary = "Get all guidance requests (viewable by farmers / experts)")
    public ResponseEntity<List<GuidanceDTO>> getAllGuidance() {
        return ResponseEntity.ok(guidanceService.getAllGuidance());
    }
}
