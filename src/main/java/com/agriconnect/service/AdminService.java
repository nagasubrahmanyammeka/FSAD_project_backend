package com.agriconnect.service;

import com.agriconnect.dto.AdminStatsDTO;
import com.agriconnect.dto.UserDTO;
import com.agriconnect.entity.User;
import com.agriconnect.exception.ResourceNotFoundException;
import com.agriconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // 👉 Optional (only if Spring Security is used)
    private final PasswordEncoder passwordEncoder;

    // ─── CREATE USER (ADD USER) ───────────────────────────────────────
    public UserDTO createUser(UserDTO userDTO) {

        User user = modelMapper.map(userDTO, User.class);

        // Encode password if security is enabled
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserDTO.class);
    }

    // ─── GET ALL USERS ────────────────────────────────────────────────
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    // ─── GET USER BY ID (Optional but useful) ─────────────────────────
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id)
                );

        return modelMapper.map(user, UserDTO.class);
    }

    // ─── UPDATE USER ──────────────────────────────────────────────────
    public UserDTO updateUser(Long id, UserDTO userDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id)
                );

        // ✅ Update ALL fields properly
        user.setName(userDTO.getName());
        user.setUsername(userDTO.getUsername());   // 🔥 VERY IMPORTANT
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setLocation(userDTO.getLocation());
        user.setRole(userDTO.getRole());
        user.setProfileImage(userDTO.getProfileImage());

        // ✅ Update password only if provided
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        }

        User updatedUser = userRepository.save(user);

        return modelMapper.map(updatedUser, UserDTO.class);
    }

    // ─── DELETE USER ─────────────────────────────────────────────────
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found with id: " + id)
                );

        userRepository.delete(user);
    }

    // ─── ADMIN DASHBOARD STATS ───────────────────────────────────────
    public AdminStatsDTO getStats() {

        long total   = userRepository.count();
        long farmers = userRepository.countByRole("farmer");
        long experts = userRepository.countByRole("expert");
        long publics = userRepository.countByRole("public");
        long admins  = userRepository.countByRole("admin");

        return AdminStatsDTO.builder()
                .totalUsers(total)
                .farmers(farmers)
                .experts(experts)
                .publicUsers(publics)
                .admins(admins)
                .build();
    }
}