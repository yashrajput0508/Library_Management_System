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

import com.example.app.model.Author;
import com.example.app.model.Member;
import com.example.app.service.AuthorService;

@RestController
@RequestMapping("authors")
public class AuthorController {
	
	private AuthorService authorService;
	
	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}
	
	@GetMapping
	public ResponseEntity<List<Author>> getallAuthors(){
		
		List<Author> authors = this.authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
	}
	
	@GetMapping("/{authorId}")
	public ResponseEntity<Author> getAuthorById(@PathVariable int authorId) {
		
		Author author = this.authorService.getAuthorById(authorId);
        if (author != null) {
            return ResponseEntity.ok(author);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	@PostMapping
	public ResponseEntity<String> addAuthor(@RequestBody Author author) {
		
		boolean added = this.authorService.addAuthor(author);
        if (added) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Author added successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add author.");
        }
	}
	
	@PutMapping("/{authorId}")
	public ResponseEntity<Void> updateAuthor(@RequestBody Author author,@PathVariable int authorId) {
		this.authorService.updateAuthor(authorId, author);
        return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{authorId}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable int authorId) {
		this.authorService.deleteAuthor(authorId);
		
        return ResponseEntity.ok().build();
	}
}
