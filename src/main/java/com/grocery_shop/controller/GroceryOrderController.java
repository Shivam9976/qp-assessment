package com.grocery_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery_shop.dto.CustomerOrderDto;
import com.grocery_shop.entity.UserDetail;
import com.grocery_shop.service.GroceryOrderService;

@RestController
@RequestMapping("/order")
public class GroceryOrderController {
	
	

	@Autowired
	GroceryOrderService groceryOrderService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody CustomerOrderDto customerOrderDto){
		return groceryOrderService.placeOrder(customerOrderDto);
	}
}
