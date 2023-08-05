package com.example.app.service;

import java.util.List;

import com.example.app.model.Checkout;

public interface CheckoutService {
	public List<Checkout> getAllCheckoutLists();
	public Checkout getCheckoutById(int checkoutId);
	public boolean addCheckout(Checkout checkout);
	public void updateCheckout(int checkoutId,Checkout checkout);
	public void deleteCheckout(int checkoutId);
}
