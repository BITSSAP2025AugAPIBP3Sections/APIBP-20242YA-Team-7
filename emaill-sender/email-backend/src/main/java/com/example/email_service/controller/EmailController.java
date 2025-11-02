package com.example.email_service.controller;

import com.example.email_service.model.EmailEntity;
import com.example.email_service.service.EmailService;

import lombok.Data;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/emails")
@CrossOrigin(origins = "*")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return emailService.healthCheck();
    }

    @GetMapping
    public List<EmailEntity> getAllEmails() {
        return emailService.getAllEmails();
    }

}