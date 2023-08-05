package com.example.app.model;

import java.io.IOException;
import java.util.Base64;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Book {
	
	@NotNull(message = "Book Id Should Not Be Null")
	@Min(value = 100, message = "Book Id Should be greater than 100")
	private int bookId;
	
	@NotNull(message = "Book Name cannot be empty")
    @Size(min = 2, max = 100, message = "Book Name must be between 2 and 100 characters")
	private String bookName;
	
	@NotNull(message = "Please select the image")
	@JsonIgnore
	private MultipartFile image;
	
	private byte[] base64Image;

	private String stringImage;
	
	@Size(min = 13, max = 13, message = "ISBN length should be should 13")
	private String ISBN;
	
	@Min(value = 1000, message = "Publication Year should be a 4-digit number")
    @Max(value = 9999, message = "Publication Year should be a 4-digit number")
	private int publicationYear;
	
	private boolean availability;
	
	@NotNull(message = "Author Cannot be Null")
	private Author author;
	
	public Book() {
		
	}
	
	public Book(int bookId, String bookName, MultipartFile image, String iSBN, int publicationYear, boolean availability,
			Author author) {
		super();
		this.bookId = bookId;
		this.bookName = bookName;
		this.image = image;
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

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
		
		try {
			this.setBase64Image(this.image.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	public String getStringImage() {
		return stringImage;
	}

	public void setStringImage(String stringImage) {
		this.stringImage = stringImage;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", bookName=" + bookName + ", image=" + image + ", base64Image=" + base64Image
				+ ", ISBN=" + ISBN + ", publicationYear=" + publicationYear + ", availability=" + availability
				+ ", author=" + author + "]";
	}
	
}
