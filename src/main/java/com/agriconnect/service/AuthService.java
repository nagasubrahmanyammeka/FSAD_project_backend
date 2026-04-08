package com.agriconnect.service;

import com.agriconnect.dto.*;
import com.agriconnect.entity.User;
import com.agriconnect.exception.InvalidCredentialsException;
import com.agriconnect.exception.UserAlreadyExistsException;
import com.agriconnect.repository.UserRepository;
import com.agriconnect.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ModelMapper modelMapper;

    // ─── REGISTER ─────────────────────────────────────────────────────
    public AuthResponseDTO register(RegisterRequestDTO dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("Username '" + dto.getUsername() + "' is already taken.");
        }
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email '" + dto.getEmail() + "' is already registered.");
        }

        User user = User.builder()
                .name(dto.getName())
                .username(dto.getUsername())
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .phone(dto.getPhone())
                .location(dto.getLocation())
                .role(dto.getRole())
                .build();

        User saved = userRepository.save(user);

        String token = jwtUtil.generateToken(saved.getUsername(), saved.getRole());
        UserDTO userDTO = modelMapper.map(saved, UserDTO.class);

        return AuthResponseDTO.builder()
                .token(token)
                .user(userDTO)
                .message("Registration successful")
                .build();
    }

    // ─── LOGIN ────────────────────────────────────────────────────────
    public AuthResponseDTO login(LoginRequestDTO dto) {
        // The frontend sends "name" field as username
        User user = userRepository.findByUsername(dto.getName())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid username or password."));

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password.");
        }

        // If role is provided in request, verify it matches
        if (dto.getRole() != null && !dto.getRole().isBlank()
                && !dto.getRole().equalsIgnoreCase(user.getRole())) {
            throw new InvalidCredentialsException(
                    "You are not registered as '" + dto.getRole() + "'. Your role is: " + user.getRole());
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);

        return AuthResponseDTO.builder()
                .token(token)
                .user(userDTO)
                .message("Login successful")
                .build();
    }

    // ─── GET ME (token → profile) ────────────────────────────────────
    public UserDTO getMe(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new InvalidCredentialsException("User not found."));
        return modelMapper.map(user, UserDTO.class);
    }
}
