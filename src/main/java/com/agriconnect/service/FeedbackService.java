package com.agriconnect.service;

import com.agriconnect.dto.FeedbackDTO;
import com.agriconnect.entity.Feedback;
import com.agriconnect.repository.FeedbackRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final ModelMapper modelMapper;

    public FeedbackDTO submitFeedback(FeedbackDTO dto) {
        Feedback entity = modelMapper.map(dto, Feedback.class);
        entity.setId(null); // ensure auto-generation
        Feedback saved = feedbackRepository.save(entity);
        FeedbackDTO result = modelMapper.map(saved, FeedbackDTO.class);
        if (saved.getCreatedAt() != null) {
            result.setCreatedAt(saved.getCreatedAt().toString());
        }
        return result;
    }

    public List<FeedbackDTO> getAllFeedbacks() {
        return feedbackRepository.findAll().stream()
                .map(f -> {
                    FeedbackDTO dto = modelMapper.map(f, FeedbackDTO.class);
                    if (f.getCreatedAt() != null) dto.setCreatedAt(f.getCreatedAt().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
