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

import com.example.app.model.Author;
import com.example.app.model.Book;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;

import reactor.core.publisher.Mono;

@Service
public class AuthorServiceImpl implements AuthorService {

	private static List<Author> authors = new ArrayList<Author>();
	private String basicAuthHeader;
	private User user;

	private final WebClient webClient;

	public AuthorServiceImpl(User user) {
		this.user = user;
		this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
	}

	@Override
	public List<Author> getAllAuthors() {

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		// TODO Auto-generated method stub
		return this.webClient.get().uri("/authors")
				.header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.retrieve().bodyToFlux(Author.class).collectList().block();
	}

	@Override
	public PaginationResult<Author> getPaginatedAuthors(int currentPage, int pageSize) {
		List<Author> allAuthors = getAllAuthors();
		int totalAuthors = allAuthors.size();

		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalAuthors);

		List<Author> paginatedAuthors = allAuthors.subList(startIndex, endIndex);

		return new PaginationResult<>(paginatedAuthors, currentPage, totalAuthors, pageSize);
	}

	@Override
	public boolean addAuthor(Author author) {
		// TODO Auto-generated method stub

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		ResponseEntity<Void> responseEntity = this.webClient.post().uri("/authors")
				.header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.body(BodyInserters.fromValue(author)).exchangeToMono(response -> {
					return response.toBodilessEntity();
				}).block();

		return responseEntity != null && responseEntity.getStatusCode() == HttpStatus.CREATED;
	}

	@Override
	public Author getAuthorById(int authorId) {
		// TODO Auto-generated method stub

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		return this.webClient.get().uri("/authors/{authorId}", authorId)
				.header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.retrieve().bodyToMono(Author.class).block();
	}

	@Override
	public void updateAuthor(int authorId, Author author) {
		// TODO Auto-generated method stub

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		this.webClient.put().uri("/authors/{authorId}", authorId).body(Mono.just(author), Author.class)
		.header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
		.retrieve()
				.bodyToMono(Void.class).block(); // Blocking call to wait for the response and get the boolean result
	}

	@Override
	public void deleteAuthor(int authorId) {
		// TODO Auto-generated method stub

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		this.webClient.delete().uri("/authors/{authorId}", authorId)
		.header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
		.retrieve().bodyToMono(Void.class)	
			.block();
	}

	private String createBasicAuthHeader(String username, String password) {

		String credentials = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
	}
}
