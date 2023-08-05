package com.example.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.app.model.Checkout;

public interface CheckoutRepository extends JpaRepository<Checkout, Integer> {

}
