package com.example.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.app.model.User;
import com.example.app.service.AuthenticationService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes({"user"})
public class LoginController {
	
	private AuthenticationService authentication;
	private User user;
	
	public LoginController(AuthenticationService authentication,User user) {
		this.authentication = authentication;
		this.user = user;
	}
	
	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String authenticatedGet(ModelMap map) {
		
		map.addAttribute("user",user);
		
		return "authentication/login";
	}
	
	@RequestMapping(value = "/",method = RequestMethod.POST)
	public String authenticatedPost(@Valid User user,BindingResult bindingResult,ModelMap map) {
		
		if(bindingResult.hasErrors()) {
			return "authentication/login";
		}
		
		if(this.authentication.isAuthenticated(user)) {
			return "redirect:/checkoutLists";
		}
		
		map.addAttribute("customError","User not Found");
		
		return "authentication/login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(@Valid User user,ModelMap map,BindingResult bindingResult,SessionStatus sessionStatus) {
		if(this.user==null) {
			return "redirect:/";
		}
		this.user.setPassword(null);
		this.user.setUsername(null);

		return "redirect:/";
	}
}
