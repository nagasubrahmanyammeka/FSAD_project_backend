package com.agriconnect.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequestDTO {

    @NotBlank(message = "Username is required")
    private String name;

    @NotBlank(message = "Password is required")
    private String password;

    private String role;
}
