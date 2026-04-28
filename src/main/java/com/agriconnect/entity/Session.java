package com.agriconnect.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    private String instructor;

    private LocalDate date;
    private LocalTime time;

    private String meetingLink;
    private String meetingId;
    private String passcode;

    // ✅ NEW FIELD (IMPORTANT)
    private String createdBy;

    @ElementCollection
    @CollectionTable(name = "session_users", joinColumns = @JoinColumn(name = "session_id"))
    @Column(name = "username")
    private Set<String> registeredUsers = new HashSet<>();
}