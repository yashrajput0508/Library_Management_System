package com.example.app.service;

import java.util.Base64;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.app.model.Checkout;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;

import reactor.core.publisher.Mono;

@Service
public class CheckoutServiceImpl implements CheckoutService {
	
	private final WebClient webClient;
	private String basicAuthHeader;
	private User user;
	
	public CheckoutServiceImpl(User user) {
		this.user = user;
		this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
	}
	
	@Override
	public List<Checkout> getAllCheckoutLists() {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
			
		return this.webClient.get()
				.uri("/checkouts")
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.retrieve()
				.bodyToFlux(Checkout.class)
				.collectList()
				.block();
	}

	@Override
	public Checkout getCheckoutById(int checkoutId) {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		return this.webClient.get()
        .uri("/checkouts/{checkoutId}", checkoutId)
        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
        .retrieve()
        .bodyToMono(Checkout.class)
        .block();
	}

	@Override
	public PaginationResult<Checkout> getPaginatedCheckoutLists(int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		List<Checkout> allCheckouts = getAllCheckoutLists();
		int totalCheckout = allCheckouts.size();

		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalCheckout);

		List<Checkout> paginatedCheckouts = allCheckouts.subList(startIndex, endIndex);

		return new PaginationResult<>(paginatedCheckouts, currentPage, totalCheckout, pageSize);
	}

	@Override
	public boolean addCheckout(Checkout checkout) {
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		ResponseEntity<Void> responseEntity = this.webClient.post()
                .uri("/checkouts")
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                .body(BodyInserters.fromValue(checkout))
                .exchangeToMono(response -> {
                    return response.toBodilessEntity();
                })
                .block();

        return responseEntity != null && responseEntity.getStatusCode() == HttpStatus.CREATED;
	}

	@Override
	public void updateCheckout(int checkoutId, Checkout checkout) {
		// TODO Auto-generated method stub
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		this.webClient.put()
         .uri("/checkouts/{checkoutId}", checkoutId)
         .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
         .body(Mono.just(checkout), Checkout.class)
         .retrieve()
         .bodyToMono(Void.class)
         .block(); // Blocking call to wait for the response and get the boolean result
	}

	@Override
	public void deleteCheckout(int checkoutId) {
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());
		
		this.webClient.delete()
        .uri("/checkouts/{checkoutId}", checkoutId)
        .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
        .retrieve()
        .bodyToMono(Void.class) // Use Void since the response has no body
        .block();
	}
	
	private String createBasicAuthHeader(String username, String password) {
		
		String credentials = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
    }

}
