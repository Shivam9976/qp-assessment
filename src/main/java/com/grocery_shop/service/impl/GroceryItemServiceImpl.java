package com.grocery_shop.service.impl;

import static com.grocery_shop.mappers.GroceryItemMapper.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.grocery_shop.api_response.ApiResponse;
import com.grocery_shop.dto.GroceryItemDto;
import com.grocery_shop.entity.Catagory;
import com.grocery_shop.entity.GroceryItem;
import com.grocery_shop.mappers.GroceryItemMapper;
import com.grocery_shop.mappers.GroceryItemMapper.*;
import com.grocery_shop.repository.CatagoryRepository;
import com.grocery_shop.repository.GroceryItemRepository;
import com.grocery_shop.service.GroceryItemService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroceryItemServiceImpl implements GroceryItemService {

	@Autowired
	GroceryItemRepository groceryItemRepository;

	@Autowired
	CatagoryRepository catagoryRepository;

	@Override
	public ResponseEntity<?> addItem(GroceryItemDto groceryItemDto) {
		try {
			log.info("adding grocery item ....");
			List<String> catagories = groceryItemDto.getCatagory().stream().map(Catagory::getCatagoryName).toList();
			List<Catagory> catagoriesAsEntity = catagoryRepository.findByCatagoryNameIn(catagories);
			GroceryItem groceryItem = TO_GROCERY_ITEM_ENTITY(groceryItemDto);
			groceryItem.setCatagory(catagoriesAsEntity);
			groceryItemDto = GroceryItemMapper.TO_GROCERY_ITEM_DTO(groceryItemRepository.save(groceryItem));
			return ResponseEntity.ok(ApiResponse.response(groceryItemDto, "Grocery Item Added Succesfully", true));
		} catch (Exception e) {
			log.error("Exception got while adding grocery Item {}", groceryItemDto.getItemName());
			log.error(e.getLocalizedMessage());
			return ResponseEntity.ok(ApiResponse.response(null, "Failed to add grocery item", false));
		}

	}

	@Override
	public ResponseEntity<?> getAllItems() {
		try {
			List<GroceryItem> groceryItems = groceryItemRepository.findAll();
			List<GroceryItemDto> groceryItemDtos = TO_GROCERY_ITEM_DTO_COLLECTION(groceryItems);
			return ResponseEntity.ok(ApiResponse.response(groceryItemDtos));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponse.responseError("Failed to get all item detais", false));
		}
	}

	@Override
	public ResponseEntity<?> deleteItemDetails(Long id) {
		try {
			groceryItemRepository.deleteById(id);
			return ResponseEntity.ok(ApiResponse.response(null, "Item details Deleted Successfully", true));
		} catch (Exception e) {
			log.error(e.getMessage());
			return ResponseEntity.ok(ApiResponse.response(null, "Item details Not Deleted", false));
		}
	}

	@Override
	public ResponseEntity<?> updateGroceryItemDetail(GroceryItemDto groceryItemDto) {
		GroceryItem groceryItem = TO_GROCERY_ITEM_ENTITY(groceryItemDto);
		try {
			groceryItemDto = TO_GROCERY_ITEM_DTO(groceryItemRepository.save(groceryItem));
			return ResponseEntity.ok(ApiResponse.response(groceryItemDto, "Details Updated Sucessfully", true));
		} catch (Exception e) {
			return ResponseEntity.ok(ApiResponse.responseError("Failed to updated item details", false));
		}
	}

}
