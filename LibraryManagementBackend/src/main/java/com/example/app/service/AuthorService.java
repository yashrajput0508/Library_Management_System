package com.example.app.service;

import java.util.List;

import com.example.app.model.Author;

public interface AuthorService {
	public List<Author> getAllAuthors();

	public boolean addAuthor(Author author);

	public Author getAuthorById(int authorId);

	public void updateAuthor(int authorId, Author author);
	
	public void deleteAuthor(int authorId);
}
