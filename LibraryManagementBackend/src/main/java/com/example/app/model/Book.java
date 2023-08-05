package com.example.app.model;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;


@Entity
public class Book {

	@Id
	private int bookId;
	
	private String bookName;
	
	@Column(columnDefinition = "MEDIUMBLOB")
	private byte[] base64Image;

	private String ISBN;
	
	private int publicationYear;
	
	private boolean availability;
	
	@ManyToOne
	private Author author;
	
	@OneToOne(mappedBy = "book")
	private Checkout checkout;
	
	public Book() {
		
	}
	
	public Book(int bookId, String bookName, String iSBN, int publicationYear, boolean availability,
			Author author) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.ISBN = iSBN;
		this.publicationYear = publicationYear;
		this.availability = availability;
		this.author = author;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	public byte[] getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(byte[] base64Image) {
		this.base64Image = base64Image;
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public int getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(int publicationYear) {
		this.publicationYear = publicationYear;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", base64Image=" + base64Image
				+ ", ISBN=" + ISBN + ", publicationYear=" + publicationYear + ", availability=" + availability
				+ ", author=" + author + "]";
	}
	
}
