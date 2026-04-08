package com.agriconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthResponseDTO {
    private String token;
    private UserDTO user;
    private String message;
}
