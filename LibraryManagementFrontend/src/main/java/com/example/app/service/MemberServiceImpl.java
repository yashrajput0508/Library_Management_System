package com.example.app.service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.app.model.Member;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;

import reactor.core.publisher.Mono;

@Service
public class MemberServiceImpl implements MemberService {

	private final WebClient webClient;
	private String basicAuthHeader;
	private User user;
	
	public MemberServiceImpl(User user) {
		this.user = user;
		this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
	}

	@Override
	public List<Member> getAllMembers() {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		return this.webClient.get()
				.uri("/members")
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.retrieve()
				.bodyToFlux(Member.class)
				.collectList()
				.block();
	}

	@Override
	public boolean addMember(Member member) {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		ResponseEntity<Void> responseEntity = this.webClient.post()
                .uri("/members")
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                .body(BodyInserters.fromValue(member))
                .exchangeToMono(response -> {
                    return response.toBodilessEntity();
                })
                .block();

        return responseEntity != null && responseEntity.getStatusCode() == HttpStatus.CREATED;
	}

	@Override
	public Member getMemberById(int memberId) {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		return this.webClient.get()
        .uri("/members/{memberId}", memberId)
        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
        .retrieve()
        .bodyToMono(Member.class)
        .block();
	}

	@Override
	public void updateMember(int memberId, Member member) {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		 this.webClient.put()
                .uri("/members/{memberId}", memberId)
                .body(Mono.just(member), Member.class)
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                .retrieve()
                .bodyToMono(Void.class)
                .block(); // Blocking call to wait for the response and get the boolean result

	}

	@Override
	public void deleteMember(int memberId) {
		// TODO Auto-generated method stub
		
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		this.webClient.delete()
        .uri("/members/{memberId}", memberId)
        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
        .retrieve()
        .bodyToMono(Void.class) // Use Void since the response has no body
        .block();
	}

	@Override
	public PaginationResult<Member> getPaginatedMembers(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		List<Member> allMembers = getAllMembers();
		int totalMembers = allMembers.size();

		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalMembers);

		List<Member> paginatedMembers = allMembers.subList(startIndex, endIndex);

		return new PaginationResult<>(paginatedMembers, currentPage, totalMembers, pageSize);
	}
	
	private String createBasicAuthHeader(String username, String password) {
        
		String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

}
