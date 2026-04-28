package com.agriconnect.service;

import com.agriconnect.dto.ContentDTO;
import com.agriconnect.entity.Content;
import com.agriconnect.exception.ResourceNotFoundException;
import com.agriconnect.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentService {

    private final ContentRepository contentRepository;

    // 🔥 CREATE WITH FILE
    public ContentDTO createContent(String author, String description, MultipartFile file) {
        try {
            Content content = Content.builder()
                    .author(author)
                    .description(description)
                    .fileName(file.getOriginalFilename())
                    .fileType(file.getContentType())
                    .data(file.getBytes())
                    .build();

            Content saved = contentRepository.save(content);

            return ContentDTO.builder()
                    .id(saved.getId())
                    .author(saved.getAuthor())
                    .description(saved.getDescription())
                    .fileName(saved.getFileName())
                    .createdAt(saved.getCreatedAt().toString())
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    // GET ALL
    public List<ContentDTO> getAllContent() {
        return contentRepository.findAll().stream()
                .map(c -> ContentDTO.builder()
                        .id(c.getId())
                        .author(c.getAuthor())
                        .description(c.getDescription())
                        .fileName(c.getFileName())
                        .createdAt(c.getCreatedAt().toString())
                        .build())
                .collect(Collectors.toList());
    }

    // DOWNLOAD
    public Content getFile(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("File not found"));
    }

    // DELETE
    public void deleteContent(Long id) {
        Content content = contentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Content not found"));
        contentRepository.delete(content);
    }
}