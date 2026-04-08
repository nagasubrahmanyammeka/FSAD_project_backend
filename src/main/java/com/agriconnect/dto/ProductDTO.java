package com.agriconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String category;
    private String imageUrl;
    private Integer stock;
}
