package com.example.app.configuration;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.example.app.service.UserInfo;

@Configuration
public class SecurityConfiguration {
	
	private final UserInfo info;
	
	public SecurityConfiguration(UserInfo info) {
		this.info = info;
	}
	
	@Bean
	public UserDetailsService createUserDetailManager() {
		
		UserDetails userDetails1 = createUser(this.info.getUsername(),this.info.getPassword());
		
		return new InMemoryUserDetailsManager(userDetails1);
	}
	
	public UserDetails createUser(String username,String password) {
		Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
		return User.builder()
				.passwordEncoder(passwordEncoder)
				.username(username)
				.password(password)
				.roles("USER","ADMIN")
				.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated()
				);
		http.httpBasic(withDefaults());
		
		http.csrf(crsf->crsf.disable());
		
		return http.build();

	}
}
