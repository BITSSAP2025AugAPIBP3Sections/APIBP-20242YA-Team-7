package com.example.vc_data_fetcher.controller;

import com.example.vc_data_fetcher.api.GithubAPI;
import com.example.vc_data_fetcher.model.VCToken;
import com.example.vc_data_fetcher.service.GithubService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
public class GithubController implements GithubAPI {

	private final GithubService githubService;

	@Value("${github.client.id}")
	private String clientId;

	@Value("${github.redirect.uri}")
	private String redirectUri;

	@Value("${frontend.url:http://localhost:3000}")
	private String frontendUrl;

	public GithubController(GithubService githubService) {
		this.githubService = githubService;
	}

	@Override
	public String login(HttpServletResponse response, Long userId) throws IOException {
		String githubAuthUrl = "https://github.com/login/oauth/authorize"
				+ "?client_id=" + clientId
				+ "&redirect_uri=" + redirectUri
				+ "&scope=repo,user";

		if (userId != null) {
			githubAuthUrl += "&state=" + userId;
		}

		return githubAuthUrl;
	}
}
