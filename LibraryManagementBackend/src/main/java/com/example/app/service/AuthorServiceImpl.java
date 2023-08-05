package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.model.Author;
import com.example.app.repository.AuthorRepository;

@Service
public class AuthorServiceImpl implements AuthorService {
	
	private AuthorRepository authorRepository;
	
	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> getAllAuthors() {
		// TODO Auto-generated method stub
		return this.authorRepository.findAll();
	}

	@Override
	public boolean addAuthor(Author author) {
		// TODO Auto-generated method stub
		
		Optional<Author> oldauthor = this.authorRepository.findById(author.getAuthorId());
		
		if(oldauthor.isEmpty()) {
			
			this.authorRepository.save(author);
			return true;
		}
		
		return false;
	}

	@Override
	public Author getAuthorById(int authorId) {
		// TODO Auto-generated method stub
		return this.authorRepository.findById(authorId).get();
	}

	@Override
	public void updateAuthor(int authorId, Author author) {
		// TODO Auto-generated method stub
		this.deleteAuthor(authorId);
		
		this.addAuthor(author);
	}

	@Override
	public void deleteAuthor(int authorId) {
		// TODO Auto-generated method stub
		this.authorRepository.deleteById(authorId);
	}

}
