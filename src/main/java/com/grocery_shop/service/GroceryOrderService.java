package com.grocery_shop.service;

import org.springframework.http.ResponseEntity;

import com.grocery_shop.dto.CustomerOrderDto;

public interface GroceryOrderService {

	public ResponseEntity<?> placeOrder(CustomerOrderDto customerOrderDto);
}
