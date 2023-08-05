package com.example.app.service;

import com.example.app.model.User;

public interface AuthenticationService {
	
	public boolean isAuthenticated(User user);
}
