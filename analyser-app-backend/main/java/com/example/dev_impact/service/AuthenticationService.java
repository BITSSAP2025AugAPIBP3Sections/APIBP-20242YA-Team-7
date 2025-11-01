package com.example.dev_impact.service;

import com.example.dev_impact.dto.AuthenticationResponseDTO;
import com.example.dev_impact.model.Token;
import com.example.dev_impact.model.User;
import com.example.dev_impact.repository.TokenRepository;
import com.example.dev_impact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class AuthenticationService {

    private final UserRepository repository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final TokenRepository tokenRepository;

    private final AuthenticationManager authenticationManager;

    @Value("${version_control_service.url}")
    private String vcsServiceUrl;

    public AuthenticationService(UserRepository repository,
                                 PasswordEncoder passwordEncoder,
                                 JwtService jwtService,
                                 TokenRepository tokenRepository,
                                 AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.tokenRepository = tokenRepository;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponseDTO register(User request) {
        if(repository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponseDTO(null, "User already exist", null);
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user = repository.save(user);
        String jwt = jwtService.generateToken(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponseDTO(jwt, "User registration was successful", user.getFirstName() + " " + user.getLastName());

    }

    public AuthenticationResponseDTO authenticate(User request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = repository.findByUsername(request.getUsername()).orElseThrow();
        String jwt = jwtService.generateToken(user);

        revokeAllTokenByUser(user);
        saveUserToken(jwt, user);

        return new AuthenticationResponseDTO(jwt, "User login was successful", user.getFirstName() + " " + user.getLastName());

    }
}
