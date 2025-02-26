package com.grocery_shop.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.grocery_shop.api_response.ApiResponse;
import com.grocery_shop.dto.CustomerOrderDto;
import com.grocery_shop.dto.CustomerOrderItemdto;
import com.grocery_shop.dto.GroceryItemDto;
import com.grocery_shop.entity.CustomerOrder;
import com.grocery_shop.entity.CustomerOrderItem;
import com.grocery_shop.entity.UserDetail;
import com.grocery_shop.mappers.CustomerOrderMapper;
import com.grocery_shop.mappers.GroceryItemMapper;
import com.grocery_shop.repository.GroceryItemRepository;
import com.grocery_shop.repository.GroceryOrderRepository;
import com.grocery_shop.repository.UserDetailRepository;
import com.grocery_shop.service.GroceryOrderService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GroceryOrderServiceImpl implements GroceryOrderService {

	@Autowired
	GroceryOrderRepository groceryOrderRepository;
	@Autowired
	GroceryItemRepository groceryItemRepository;
	@Autowired
	UserDetailRepository userDetailRepository;

	@Override
	@Transactional
	public ResponseEntity<?> placeOrder(CustomerOrderDto customerOrderDto) {
		try {
			log.info("placing order ....");
			List<GroceryItemDto> orderedItremDetails = new ArrayList<>();
			setAllItemsDetailsToCustomerOrderItems(customerOrderDto, orderedItremDetails);
			CustomerOrder customerOrder = CustomerOrderMapper.TO_CUSTOMER_ORDER_ENTITY(customerOrderDto);
			setQuantityAndAndOrderPriceToOrder(customerOrder);
			groceryItemRepository.saveAll(orderedItremDetails.stream().map(GroceryItemMapper::TO_GROCERY_ITEM_ENTITY).toList());
			customerOrderDto = CustomerOrderMapper.TO_CUSTOMER_ORDER_DTO(groceryOrderRepository.save(customerOrder));
			customerOrderDto.setUserDetail(null);
			return ResponseEntity.ok(ApiResponse.response(customerOrderDto, "Order Placed Sucessfully...", true));
		} catch (Exception e) {
			log.error("Exception got while placing order.. {}", customerOrderDto.toString());
			log.error(e.getLocalizedMessage());
			return ResponseEntity.ok(ApiResponse.responseError("Failed to place order...", false));
		}
	}
	
	private void setAllItemsDetailsToCustomerOrderItems(CustomerOrderDto customerOrderDto, List<GroceryItemDto> groceryItemDtos) {
		for(CustomerOrderItemdto c : customerOrderDto.getCustomerOrderItem()) {
			GroceryItemDto groceryItemDto = GroceryItemMapper.TO_GROCERY_ITEM_DTO(groceryItemRepository.findByItemName(c.getItemName()));
			c.setItemName(groceryItemDto.getItemName());
			long quantity = groceryItemDto.getQuantity() - c.getQuantity();
			if(quantity > 0)
			groceryItemDto.setQuantity(quantity);
			else throw new NullPointerException();
			c.setPrice(groceryItemDto.getPrice());
			groceryItemDtos.add(groceryItemDto);
		}
		
	}
	
	private void setQuantityAndAndOrderPriceToOrder(CustomerOrder customerOrder) {
		double orderPrice = 0d;
		customerOrder.setUserId(getUserDetailFromCurrentToken());
		for(CustomerOrderItem c : customerOrder.getCustomerOrderItem()) {
			orderPrice += c.getPrice() * c.getQuantity();
		}
		customerOrder.setOrderTotalPrice(orderPrice);
	}

	private UserDetail getUserDetailFromCurrentToken() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserDetail detail = userDetailRepository.findByEmailId(authentication.getName());
		return detail;
	}
}
