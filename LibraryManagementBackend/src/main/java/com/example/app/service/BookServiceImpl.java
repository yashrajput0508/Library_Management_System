package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.model.Book;
import com.example.app.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public List<Book> getAllBooks() {
		// TODO Auto-generated method stub
		return this.bookRepository.findAll();
	}

	@Override
	public Book getBookById(int bookId) {
		// TODO Auto-generated method stub
		return this.bookRepository.findById(bookId).get();
	}

	@Override
	public boolean addBook(Book book) {
		// TODO Auto-generated method stub
		
		Optional<Book> oldbook = this.bookRepository.findById(book.getBookId());
		
		if(oldbook.isEmpty()) {
			
			this.bookRepository.save(book);
			return true;
		}
		
		return false;
	}

	@Override
	public void updateBook(int bookId, Book book) {
		// TODO Auto-generated method stub
		
		
		this.deleteBook(bookId);
		
		this.addBook(book);
	}

	@Override
	public void deleteBook(int bookId) {
		// TODO Auto-generated method stub
		this.bookRepository.deleteById(bookId);
	}

	@Override
	public void updateAvailability(int bookId, boolean available) {
		// TODO Auto-generated method stub
		
		Book book = this.getBookById(bookId);
		book.setAvailability(available);
		
		this.bookRepository.save(book);
	}

}
