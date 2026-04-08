package com.agriconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phone;
    private String location;
    
    private String role;
    private String profileImage;
}
