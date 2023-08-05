package com.example.app.service;

import java.util.Base64;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.app.model.User;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private WebClient webClient;
	private User user;
	
	public AuthenticationServiceImpl(User user) {
		this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
		this.user = user;
	}
	
	@Override
	public boolean isAuthenticated(User user) {
		try {
            String basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
            webClient.post()
                    .uri("/login") // Replace with your authentication endpoint
                    .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                    .retrieve()
                    .bodyToMono(Void.class)
                    .block();
            
            // If no exception occurred, authentication is successful
            return true;
        } catch (Exception e) {
        	System.out.println(false);
            // Handle authentication failure (e.g., log the error or display an error message)
            return false;
        }
	}
	
	private String createBasicAuthHeader(String username, String password) {
        
		String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

}
