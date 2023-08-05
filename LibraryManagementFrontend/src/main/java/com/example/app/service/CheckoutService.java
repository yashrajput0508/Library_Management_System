package com.example.app.service;

import java.util.List;

import com.example.app.model.Book;
import com.example.app.model.Checkout;
import com.example.app.model.PaginationResult;

public interface CheckoutService {
	
	public List<Checkout> getAllCheckoutLists();
	public Checkout getCheckoutById(int checkoutId);
	public PaginationResult<Checkout> getPaginatedCheckoutLists(int currentPage, int pageSize);
	public boolean addCheckout(Checkout checkout);
	public void updateCheckout(int checkoutId,Checkout checkout);
	public void deleteCheckout(int checkoutId);
}
