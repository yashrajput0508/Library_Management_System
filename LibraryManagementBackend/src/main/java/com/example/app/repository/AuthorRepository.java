package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
