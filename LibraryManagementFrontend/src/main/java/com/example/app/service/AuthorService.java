package com.example.app.service;

import java.util.List;

import com.example.app.model.Author;
import com.example.app.model.PaginationResult;

public interface AuthorService {
	
	public List<Author> getAllAuthors();
	
	public boolean addAuthor(Author author);
	
	public Author getAuthorById(int authorId);
	
	public void updateAuthor(int authorId,Author author);
	
	public void deleteAuthor(int authorId);
	
	public PaginationResult<Author> getPaginatedAuthors(int currentPage, int pageSize);
}
