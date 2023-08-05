package com.example.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.model.Checkout;
import com.example.app.service.CheckoutService;

@RestController
@RequestMapping("checkouts")
public class CheckoutController {
	
	private CheckoutService checkoutService;
	
	public CheckoutController(CheckoutService checkoutService) {
		this.checkoutService = checkoutService;
	}
	
	@GetMapping
	public ResponseEntity<List<Checkout>> getallCheckouts(){
	
		List<Checkout> checkouts = this.checkoutService.getAllCheckoutLists();
		return ResponseEntity.ok(checkouts);
	}
	
	@GetMapping("/{checkoutId}")
	public ResponseEntity<Checkout> getCheckoutById(@PathVariable int checkoutId) {
		
		Checkout checkout = this.checkoutService.getCheckoutById(checkoutId);
		if (checkout != null) {
			return ResponseEntity.ok(checkout);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<String> addCheckout(@RequestBody Checkout checkout) {
		
		boolean added = this.checkoutService.addCheckout(checkout);
		if (added) {
			return ResponseEntity.status(HttpStatus.CREATED).body("Checkout added successfully!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add checkout.");
		}
	}
	
	@PutMapping("/{checkoutId}")
	public ResponseEntity<Void> updateCheckout(@RequestBody Checkout checkout,@PathVariable int checkoutId) {
		
		this.checkoutService.updateCheckout(checkoutId, checkout);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{checkoutId}")
	public ResponseEntity<Void> deleteCheckout(@PathVariable int checkoutId) {
		
		this.checkoutService.deleteCheckout(checkoutId);
		return ResponseEntity.ok().build();
	}
}
