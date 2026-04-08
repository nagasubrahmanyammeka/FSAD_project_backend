package com.agriconnect.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStatsDTO {
    private long totalUsers;
    private long farmers;
    private long experts;
    private long publicUsers;
    private long admins;
}
