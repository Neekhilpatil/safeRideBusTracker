package com.saferidetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saferidetracker.dtos.EmailRequestDto;
import com.saferidetracker.service.EmailService;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/send-email")
    public String sendEmail() {
        emailService.sendSimpleEmail("recipientEmail-receiver", "Test Subject", "This is a test email.");
        return "Email sent successfully!";
    }
    
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequestDto emailRequest) {
        emailService.sendSimpleEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getBody());
        return "Email sent successfully!";
    }
}
