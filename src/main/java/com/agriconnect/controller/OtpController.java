package com.agriconnect.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agriconnect.service.OtpService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
@CrossOrigin("*")
public class OtpController {

    private final OtpService otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) {
        otpService.generateOtp(email);
        return "OTP sent to email";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String email,
                            @RequestParam String otp) {

        boolean valid = otpService.verifyOtp(email, otp);

        return valid ? "OTP Verified" : "Invalid OTP";
    }
}