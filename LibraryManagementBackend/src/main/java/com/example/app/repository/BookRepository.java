package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
