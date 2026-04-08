package com.agriconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentDTO {
    private Long id;
    private String author;
    private String description;
    private String fileName;
    private String createdAt;
}
