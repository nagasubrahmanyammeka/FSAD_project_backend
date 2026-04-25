package com.agriconnect.service;


import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    public void sendRegistrationSuccess(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);
        message.setSubject("🎉 Registration Successful - AgriConnect");

        message.setText(
                "Hello, Welcome To AgriConnect"+
                "Your registration was successful!\n\n" +
                "Welcome to AgriConnect 🌱\n\n" +
                "You can now login and start using the platform.\n\n" +
                "Thank you!"
        );

        mailSender.send(message);
        System.out.println("EMAIL METHOD CALLED");
        System.out.println("Sending to: " + toEmail);
    }
}