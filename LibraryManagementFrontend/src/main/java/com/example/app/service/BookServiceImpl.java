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

import com.example.app.model.Book;
import com.example.app.model.Checkout;
import com.example.app.model.PaginationResult;
import com.example.app.model.User;

import reactor.core.publisher.Mono;

@Service
public class BookServiceImpl implements BookService {

	private final WebClient webClient;
	private String basicAuthHeader;
	private User user;

	public BookServiceImpl(User user) {
		this.user = user;
		this.webClient = WebClient.builder().baseUrl("http://localhost:8083").build();
	}

	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		return this.webClient.get().uri("/books").header(HttpHeaders.AUTHORIZATION, basicAuthHeader).retrieve()
				.bodyToFlux(Book.class).collectList().block();
	}

	@Override
	public PaginationResult<Book> getPaginatedBooks(int currentPage, int pageSize) {
		List<Book> allBooks = getAllBooks();
		int totalBooks = allBooks.size();

		int startIndex = (currentPage - 1) * pageSize;
		int endIndex = Math.min(startIndex + pageSize, totalBooks);

		List<Book> paginatedBooks = allBooks.subList(startIndex, endIndex);

		return new PaginationResult<>(paginatedBooks, currentPage, totalBooks, pageSize);
	}

	@Override
	public boolean addBook(Book book) {

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		ResponseEntity<Void> responseEntity = this.webClient.post().uri("/books")
				.header(HttpHeaders.AUTHORIZATION, basicAuthHeader).body(BodyInserters.fromValue(book))
				.exchangeToMono(response -> {
					return response.toBodilessEntity();
				}).block();

		return responseEntity != null && responseEntity.getStatusCode() == HttpStatus.CREATED;
	}

	@Override
	public Book getBookById(int bookId) {
		// TODO Auto-generated method stub

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		return this.webClient.get().uri("/books/{bookId}", bookId).header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.retrieve().bodyToMono(Book.class).block();
	}

	@Override
	public void updateBook(int bookId, Book book) {

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		if (book.getImage().isEmpty()) {
			Book oldBook = this.getBookById(bookId);
			book.setBase64Image(oldBook.getBase64Image());
		}

		this.webClient.put().uri("/books/{bookId}", bookId).header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.body(Mono.just(book), Book.class).retrieve().bodyToMono(Void.class).block(); // Blocking call to wait
																							
	}

	@Override
	public void updateAvailability(int bookId, boolean availability) {
		// TODO Auto-generated method stub
		
		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		this.webClient.put().uri("/books/availability/{bookId}", bookId)
		.header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.body(Mono.just(availability), Book.class)
				.retrieve().bodyToMono(Void.class).block(); // Blocking call to wait
	}

	@Override
	public void deleteBook(int bookId) {

		this.basicAuthHeader = createBasicAuthHeader(user.getUsername(), user.getPassword());

		this.webClient.delete().uri("/books/{bookId}", bookId).header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
				.retrieve().bodyToMono(Void.class) // Use Void since the response has no body
				.block();
	}

	private String createBasicAuthHeader(String username, String password) {

		String credentials = username + ":" + password;
		return "Basic " + Base64.getEncoder().encodeToString(credentials.getBytes());
	}

}
