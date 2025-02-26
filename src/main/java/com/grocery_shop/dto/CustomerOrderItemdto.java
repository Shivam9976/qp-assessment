package com.grocery_shop.dto;

import com.grocery_shop.entity.CustomerOrder;
import com.grocery_shop.entity.GroceryItem;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderItemdto {
	
	private Long customerOrderItemId;
	private CustomerOrderDto order;
	private String itemName;
	private long quantity;
	private double price;
}
