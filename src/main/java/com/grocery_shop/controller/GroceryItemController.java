package com.grocery_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grocery_shop.dto.GroceryItemDto;
import com.grocery_shop.service.GroceryItemService;


@RestController
@RequestMapping("/item")
public class GroceryItemController {

	@Autowired
	GroceryItemService groceryItemService;
	
	@PostMapping("/addItem")
	public ResponseEntity<?> addItem(@RequestBody GroceryItemDto groceryItemDto){
		return groceryItemService.addItem(groceryItemDto);
	}
	
	@GetMapping("/viewAllItems")
	public ResponseEntity<?> getAllItems(){
		return groceryItemService.getAllItems();
	}
	
	@DeleteMapping("/deleteItem/{id}")
	public ResponseEntity<?> deleteItemDetails(@PathVariable("id") Long id) {
		return groceryItemService.deleteItemDetails(id);
	}
	
	@PutMapping("/updateItemDetail")
	public ResponseEntity<?> updateGroceryItemDetail(@RequestBody GroceryItemDto groceryItemDto){
		return groceryItemService.updateGroceryItemDetail(groceryItemDto);
	}
}
