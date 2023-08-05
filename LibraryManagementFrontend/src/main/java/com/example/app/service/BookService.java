package com.example.app.service;

import java.util.List;

import com.example.app.model.Book;
import com.example.app.model.PaginationResult;

public interface BookService {
	public List<Book> getAllBooks();
	public Book getBookById(int bookId);
	public PaginationResult<Book> getPaginatedBooks(int currentPage, int pageSize);
	public boolean addBook(Book book);
	public void updateBook(int bookId,Book book);
	public void updateAvailability(int bookId,boolean availability);
	public void deleteBook(int bookId);
	
}
