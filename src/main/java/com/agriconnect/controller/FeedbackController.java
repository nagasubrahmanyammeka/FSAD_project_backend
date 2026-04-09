package com.agriconnect.controller;

import com.agriconnect.dto.FeedbackDTO;
import com.agriconnect.service.FeedbackService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedbacks")
@RequiredArgsConstructor
@Tag(name = "Feedback", description = "Submit and view user feedback")
public class FeedbackController {

    private final FeedbackService feedbackService;

    
    @PostMapping
    @Operation(summary = "Submit feedback (open — no auth required)")
    public ResponseEntity<FeedbackDTO> submitFeedback(@Valid @RequestBody FeedbackDTO dto) {
        return ResponseEntity.ok(feedbackService.submitFeedback(dto));
    }

    
    @GetMapping
    @Operation(summary = "Get all feedbacks (admin view)")
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
    }
}
