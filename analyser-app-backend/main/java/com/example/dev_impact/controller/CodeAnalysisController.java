package com.example.dev_impact.controller;

import com.example.dev_impact.model.CodeAnalysis;
import com.example.dev_impact.model.User;
import com.example.dev_impact.service.CodeAnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CodeAnalysisController {

    @Autowired
    private CodeAnalysisService codeAnalysisService;

    @PostMapping("/analyze")
    public HashMap<String, String> analyzeCode(String repoUrl, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return codeAnalysisService.startAnalyzingCode(repoUrl, user);
    }

    @PostMapping("/analysis/callback/{id}")
    public boolean receiveAnalysisResult(@PathVariable Long id, @RequestBody String result) {
        return codeAnalysisService.handleAnalysisCallback(id, result);
    }

    @GetMapping("/analyses")
    public List<CodeAnalysis> getAllAnalyses(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return codeAnalysisService.getAllAnalysesForUser(user);
    }
}
