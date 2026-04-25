package com.agriconnect.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final JavaMailSender mailSender;

    private Map<String, String> otpStorage = new HashMap<>();

    public String generateOtp(String email) {
        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        otpStorage.put(email, otp);

        sendEmail(email, otp);

        return otp;
    }

    private void sendEmail(String to, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Your OTP Code");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);
    }

    public boolean verifyOtp(String email, String otp) {
        return otp.equals(otpStorage.get(email));
    }
}