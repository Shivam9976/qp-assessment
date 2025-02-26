package com.grocery_shop.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.grocery_shop.dto.GroceryItemDto;
import com.grocery_shop.entity.GroceryItem;

public class GroceryItemMapper {

	 
	
	public static GroceryItemDto TO_GROCERY_ITEM_DTO(GroceryItem groceryItem) {
		return new ModelMapper().map(groceryItem, GroceryItemDto.class);
	}
	
	public static GroceryItem TO_GROCERY_ITEM_ENTITY(GroceryItemDto groceryItemDto) {
		return new ModelMapper().map(groceryItemDto, GroceryItem.class);
	}
	
	public static List<GroceryItemDto> TO_GROCERY_ITEM_DTO_COLLECTION(List<GroceryItem> groceryItems) {
		return new ModelMapper().map(groceryItems, new TypeToken<List<GroceryItemDto>>() {} .getType());
	}
	
	public static List<GroceryItem> TO_GROCERY_ITEM_ENTITY_COLLECTION(List<GroceryItemDto> groceryItemDtos) {
		return new ModelMapper().map(groceryItemDtos, new TypeToken<List<GroceryItem>>() {} .getType());
	}
}
