package com.example.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Book;
import com.example.app.model.Checkout;
import com.example.app.service.BookService;
import com.example.app.service.CheckoutService;

@RestController
@RequestMapping("books")
public class BookController {

	private BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public ResponseEntity<List<Book>> getallBooks() {
		
		List<Book> books = this.bookService.getAllBooks();
        return ResponseEntity.ok(books);
	}

	@GetMapping("/{bookId}")
	public ResponseEntity<Book> getBookById(@PathVariable int bookId) {
		
		Book book = this.bookService.getBookById(bookId);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
	}

	@PostMapping
	public ResponseEntity<String> addBook(@RequestBody Book book) {
		
		boolean added = this.bookService.addBook(book);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add book.");
        }
	}

	@PutMapping("/{bookId}")
	public ResponseEntity<Void> updateBook(@RequestBody Book book, @PathVariable int bookId) {
		
		this.bookService.updateBook(bookId, book);
        return ResponseEntity.ok().build();
	}
	
	@PutMapping("/availability/{bookId}")
	public ResponseEntity<Void> updateAvailablility(@RequestBody boolean available, @PathVariable int bookId) {
		this.bookService.updateAvailability(bookId, available);
        return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{bookId}")
	public ResponseEntity<Void> deleteBook(@PathVariable int bookId) {
		
		this.bookService.deleteBook(bookId);
        return ResponseEntity.ok().build();
	}
}
