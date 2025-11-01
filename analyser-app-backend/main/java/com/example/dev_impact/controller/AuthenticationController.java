package com.example.dev_impact.controller;

import com.example.dev_impact.dto.AuthenticationResponseDTO;
import com.example.dev_impact.model.User;
import com.example.dev_impact.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationService authService;

    @PostMapping("/auth/register")
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody User request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody User request) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
