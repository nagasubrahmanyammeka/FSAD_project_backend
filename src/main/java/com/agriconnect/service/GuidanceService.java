package com.agriconnect.service;

import com.agriconnect.dto.GuidanceDTO;
import com.agriconnect.entity.Guidance;
import com.agriconnect.repository.GuidanceRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GuidanceService {

    private final GuidanceRepository guidanceRepository;
    private final ModelMapper modelMapper;

    public GuidanceDTO submitGuidance(GuidanceDTO dto) {
        Guidance entity = modelMapper.map(dto, Guidance.class);
        entity.setId(null);
        Guidance saved = guidanceRepository.save(entity);
        GuidanceDTO result = modelMapper.map(saved, GuidanceDTO.class);
        if (saved.getCreatedAt() != null) {
            result.setCreatedAt(saved.getCreatedAt().toString());
        }
        return result;
    }

    public List<GuidanceDTO> getAllGuidance() {
        return guidanceRepository.findAll().stream()
                .map(g -> {
                    GuidanceDTO dto = modelMapper.map(g, GuidanceDTO.class);
                    if (g.getCreatedAt() != null) dto.setCreatedAt(g.getCreatedAt().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }
}
