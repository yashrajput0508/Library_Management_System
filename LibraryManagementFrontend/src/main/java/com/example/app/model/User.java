package com.example.app.model;

import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;

@Component
public class User {
	
	@NotBlank(message = "Username is required")
	private String username;
	
	@NotBlank(message = "Password is required")
	private String password;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}
}
