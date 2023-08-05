package com.example.app.service;

import java.util.List;

import com.example.app.model.Book;

public interface BookService {
	public List<Book> getAllBooks();
	public Book getBookById(int bookId);
	public boolean addBook(Book book);
	public void updateBook(int bookId,Book book);
	public void updateAvailability(int bookId,boolean available);
	public void deleteBook(int bookId);
}
