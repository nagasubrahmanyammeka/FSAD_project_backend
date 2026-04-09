package com.agriconnect.controller;

import com.agriconnect.dto.ContentDTO;
import com.agriconnect.service.ContentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
@Tag(name = "Content", description = "Expert-uploaded educational content")
public class ContentController {

    private final ContentService contentService;

    
    @PostMapping
    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "Upload new content (expert / admin only)")
    public ResponseEntity<ContentDTO> createContent(@RequestBody ContentDTO dto) {
        return ResponseEntity.ok(contentService.createContent(dto));
    }

    
    @GetMapping
    @Operation(summary = "Get all content (public)")
    public ResponseEntity<List<ContentDTO>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    
    @DeleteMapping("/{id}")
    @SecurityRequirement(name = "BearerAuth")
    @Operation(summary = "Delete content by ID (admin / expert only)")
    public ResponseEntity<Map<String, String>> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok(Map.of("message", "Content deleted successfully"));
    }
}
