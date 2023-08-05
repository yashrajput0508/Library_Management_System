package com.example.app.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Author {
	
	@Min(value = 1, message = "Author Id Should be greater than 0")
	private int authorId;

	@Size(min = 2, max = 20, message = "Author Name Size exists between 2 to 20")
	private String authorName;
	
	@Size(min = 5, max = 200, message = "Author Description Size Exists between 5 to 50")
	private String description;
	
	public Author() {
		
	}
	
	public Author(int authorId, String authorName,String description) {
		super();
		this.authorId = authorId;
		this.authorName = authorName;
		this.description = description;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Author [authorId=" + authorId + ", authorName=" + authorName + ", description=" + description + "]";
	}

	
}
