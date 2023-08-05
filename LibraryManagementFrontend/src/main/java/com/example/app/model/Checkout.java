package com.example.app.model;

import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;

public class Checkout {
	
	@NotNull(message = "Checkout ID should not be null")
	@Min(value = 1,message = "Checkout Id should be greater than 1")
	private int checkoutId;
	
	@NotNull(message = "Book Should not be null")
	private Book book;
	
	@NotNull(message = "Member Should not be null")
	private Member member;
	
	@NotNull(message = "Checkout Date Should not be null")
	@PastOrPresent(message = "Checkout Date should not be future date")
	private LocalDate checkoutDate;
	
	@NotNull(message = "Due Date Should not be null")
	@FutureOrPresent(message = "Due Date should not be past date")
	private LocalDate dueDate;
	
	@PastOrPresent(message = "Return Date should not be future data")
	private LocalDate returnDate;
	
	public Checkout() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Checkout(int checkoutId, Book book, Member member, LocalDate checkoutDate, LocalDate dueDate,
			LocalDate returnDate) {
		super();
		this.checkoutId = checkoutId;
		this.book = book;
		this.member = member;
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.returnDate = returnDate;
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Checkout [checkoutId=" + checkoutId + ", book=" + book + ", member=" + member + ", checkoutDate="
				+ checkoutDate + ", dueDate=" + dueDate + ", returnDate=" + returnDate + "]";
	}

}
