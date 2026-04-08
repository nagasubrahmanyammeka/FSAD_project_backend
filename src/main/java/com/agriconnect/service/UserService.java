package com.agriconnect.service;

import com.agriconnect.dto.UserDTO;
import com.agriconnect.entity.User;
import com.agriconnect.exception.ResourceNotFoundException;
import com.agriconnect.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // ─── Get by ID ────────────────────────────────────────────────────
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return modelMapper.map(user, UserDTO.class);
    }

    // ─── Update profile ───────────────────────────────────────────────
    public UserDTO updateUser(Long id, UserDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (dto.getName()     != null) user.setName(dto.getName());
        if (dto.getPhone()    != null) user.setPhone(dto.getPhone());
        if (dto.getLocation() != null) user.setLocation(dto.getLocation());
        if (dto.getProfileImage() != null) user.setProfileImage(dto.getProfileImage());

        User updated = userRepository.save(user);
        return modelMapper.map(updated, UserDTO.class);
    }

    // ─── Delete own account ───────────────────────────────────────────
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        userRepository.delete(user);
    }
}
