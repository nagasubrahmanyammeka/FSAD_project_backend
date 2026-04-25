package com.agriconnect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agriconnect.service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final EmailService emailService;

    @GetMapping("/mail")
    public String testMail() {
        emailService.sendRegistrationSuccess("agriconnectwork@gmail.com", "Sudheer");
        return "Mail Triggered";
    }
}
