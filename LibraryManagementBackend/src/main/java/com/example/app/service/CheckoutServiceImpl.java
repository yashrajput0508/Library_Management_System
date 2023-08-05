package com.example.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.app.model.Checkout;
import com.example.app.repository.CheckoutRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

	private CheckoutRepository checkoutRepository;
	
	public CheckoutServiceImpl(CheckoutRepository checkoutRepository) {
		this.checkoutRepository = checkoutRepository;
	}
	
	@Override
	public List<Checkout> getAllCheckoutLists() {
		// TODO Auto-generated method stub
		return this.checkoutRepository.findAll();
	}

	@Override
	public Checkout getCheckoutById(int checkoutId) {
		// TODO Auto-generated method stub
		return this.checkoutRepository.findById(checkoutId).get();
	}

	@Override
	public boolean addCheckout(Checkout checkout) {
		// TODO Auto-generated method stub
		
		Optional<Checkout> oldcheckout = this.checkoutRepository.findById(checkout.getCheckoutId());
		
		if(oldcheckout.isEmpty()) {
			
			this.checkoutRepository.save(checkout);
			
			return true;
		}
		
		return false;
	}

	@Override
	public void updateCheckout(int checkoutId, Checkout checkout) {
		// TODO Auto-generated method stub
		this.checkoutRepository.save(checkout);
		
	}

	@Override
	public void deleteCheckout(int checkoutId) {
		// TODO Auto-generated method stub
		this.checkoutRepository.deleteById(checkoutId);
	}

}
