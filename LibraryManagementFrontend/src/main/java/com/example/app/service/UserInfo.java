package com.example.app.service;

import org.springframework.stereotype.Component;

@Component
public class UserInfo {
	
	private String username;
	private String password;
	
	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String username, String password) {
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
		return "UserInfo [username=" + username + ", password=" + password + "]";
	}
}
