package com.example.dev_impact.service;

import com.example.dev_impact.constant.Message;
import com.example.dev_impact.model.AnalysisStatus;
import com.example.dev_impact.model.CodeAnalysis;
import com.example.dev_impact.model.User;
import com.example.dev_impact.repository.CodeAnalysisRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;

@Service
public class CodeAnalysisService {

    private CodeAnalysisRepository codeAnalysisRepository;

    @Value("${version_control_service.url}")
    private String vcsServiceUrl;

    @Value("${code_analyzer_service.url}")
    private String analyzerServiceUrl;

    @Value("${application.url}")
    private String appUrl;

    @Value("${email_service.url}")
    private String emailServiceUrl;

    @Value("${email_sender_address}")
    private String emailSenderAddress;

    public CodeAnalysisService(CodeAnalysisRepository codeAnalysisRepository) {
        this.codeAnalysisRepository = codeAnalysisRepository;
    }

    public HashMap<String, String> startAnalyzingCode(String repoUrl, User user) {
        HashMap<String, String> response = new HashMap<>();
        try {
            if (!isValidRepo(repoUrl, user)) {
                response.put("status", "error");
                response.put("message", Message.INVALID_REPO_URL);
                return response;
            }

            CodeAnalysis codeAnalysis = new CodeAnalysis();
            codeAnalysis.setRepoUrl(repoUrl);
            codeAnalysis.setUser(user);
            codeAnalysis.setStatus(AnalysisStatus.IN_PROGRESS);
            codeAnalysisRepository.save(codeAnalysis);

            requestCodeAnalysis(repoUrl, codeAnalysis);

            response.put("status", "success");
            response.put("message", Message.ANALYSIS_IN_PROGRESS);
            return response;
        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", Message.ANALYSIS_FAILED);
            return response;
        }
    }

    public boolean handleAnalysisCallback(Long analysisId, String result) {
        try {
            CodeAnalysis codeAnalysis = codeAnalysisRepository.findById(analysisId).orElseThrow();
            codeAnalysis.setStatus(AnalysisStatus.COMPLETED);
            codeAnalysis.setResult(result);
            codeAnalysisRepository.save(codeAnalysis);
            sendEmailNotification(codeAnalysis.getUser(), Message.ANALYSIS_COMPLETED_EMAIL_SUBJECT, Message.ANALYSIS_COMPLETED_EMAIL_BODY.replace("{repoUrl}", codeAnalysis.getRepoUrl()));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<CodeAnalysis> getAllAnalysesForUser(User user) {
        return codeAnalysisRepository.findByUserId(user.getId());
    }

    private void sendEmailNotification(User user, String subject, String body) {
        try {
            String boundary = "----Boundary" + System.currentTimeMillis();

            // Build multipart/form-data body
            String formData =
                    "--" + boundary + "\r\n" +
                            "Content-Disposition: form-data; name=\"fromAddress\"\r\n\r\n" +
                            emailSenderAddress + "\r\n" +
                            "--" + boundary + "\r\n" +
                            "Content-Disposition: form-data; name=\"toAddress\"\r\n\r\n" +
                            user.getUsername() + "\r\n" +
                            "--" + boundary + "\r\n" +
                            "Content-Disposition: form-data; name=\"subject\"\r\n\r\n" +
                            subject + "\r\n" +
                            "--" + boundary + "\r\n" +
                            "Content-Disposition: form-data; name=\"body\"\r\n\r\n" +
                            body + "\r\n" +
                            "--" + boundary + "--\r\n";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(emailServiceUrl + "/api/emails"))
                    .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                    .POST(HttpRequest.BodyPublishers.ofString(formData))
                    .build();

            HttpClient client = HttpClient.newHttpClient();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Email API Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

        } catch (URISyntaxException | IOException | InterruptedException e) {
            System.err.println("Failed to send email notification: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean isValidRepo(String repoUrl, User user) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(vcsServiceUrl + "/api/repo-validate"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString("{\"repoUrl\":\"" + repoUrl + "\", \"userId\":\"" + user.getId() + "\"}"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.statusCode() == 200;
        } catch (URISyntaxException | IOException | InterruptedException e) {
            return false;
        }
    }

    private void requestCodeAnalysis(String repoUrl, CodeAnalysis codeAnalysis) {
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(analyzerServiceUrl + "/process/"))
                    .POST(HttpRequest.BodyPublishers.ofString("{\"repo_url\":\"" + repoUrl + "\", \"call_back_url" +
                            "\":\"" + appUrl + "/api/analysis/callback/" + codeAnalysis.getId() + "\", \"user_id\":\"" + codeAnalysis.getUser().getId()+ "\"}"))
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            response.statusCode();
        } catch (URISyntaxException | IOException | InterruptedException e) {
        }
    }
}
