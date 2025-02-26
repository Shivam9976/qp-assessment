package com.grocery_shop.service;

import org.springframework.http.ResponseEntity;

import com.grocery_shop.dto.GroceryItemDto;

public interface GroceryItemService {

	public ResponseEntity<?> addItem(GroceryItemDto groceryItemDto);

	public ResponseEntity<?> getAllItems();

	public ResponseEntity<?> deleteItemDetails(Long id);

	public ResponseEntity<?> updateGroceryItemDetail(GroceryItemDto groceryItemDto);
}
