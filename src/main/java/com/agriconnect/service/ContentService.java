package com.agriconnect.service;

import com.agriconnect.dto.ContentDTO;
import com.agriconnect.entity.Content;
import com.agriconnect.exception.ResourceNotFoundException;
import com.agriconnect.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;
    private final ModelMapper modelMapper;

    public ContentDTO createContent(ContentDTO dto) {
        Content entity = modelMapper.map(dto, Content.class);
        entity.setId(null);
        Content saved = contentRepository.save(entity);
        ContentDTO result = modelMapper.map(saved, ContentDTO.class);
        if (saved.getCreatedAt() != null) result.setCreatedAt(saved.getCreatedAt().toString());
        return result;
    }

    public List<ContentDTO> getAllContent() {
        return contentRepository.findAll().stream()
                .map(c -> {
                    ContentDTO dto = modelMapper.map(c, ContentDTO.class);
                    if (c.getCreatedAt() != null) dto.setCreatedAt(c.getCreatedAt().toString());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public void deleteContent(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found with id: " + id));
        contentRepository.delete(content);
    }
}
