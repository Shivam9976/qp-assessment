package com.grocery_shop.dto;

import java.util.List;

import com.grocery_shop.entity.Catagory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroceryItemDto {

	private Long itemId;
	private String itemName;
	private double price;
	private Long quantity;
	private List<Catagory> catagory;
}
