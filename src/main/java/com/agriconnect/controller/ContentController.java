package com.agriconnect.controller;

import com.agriconnect.dto.ContentDTO;
import com.agriconnect.entity.Content;
import com.agriconnect.service.ContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/content")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ContentController {

    private final ContentService contentService;

    // 🔥 UPLOAD (NO SECURITY ISSUE)
    @PostMapping("/upload")
    public ResponseEntity<ContentDTO> uploadContent(
            @RequestParam("author") String author,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(
                contentService.createContent(author, description, file)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<ContentDTO>> getAllContent() {
        return ResponseEntity.ok(contentService.getAllContent());
    }

    // DOWNLOAD
    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long id) {

        Content file = contentService.getFile(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFileName() + "\"")
                .body(file.getData());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteContent(@PathVariable Long id) {
        contentService.deleteContent(id);
        return ResponseEntity.ok(Map.of("message", "Deleted"));
    }
}