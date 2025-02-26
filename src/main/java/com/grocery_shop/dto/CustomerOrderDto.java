package com.grocery_shop.dto;

import java.util.List;

import com.grocery_shop.entity.CustomerOrderItem;
import com.grocery_shop.entity.UserDetail;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerOrderDto {

	private Long orderId;
	
	private UserDetail userDetail;
	
	private String orderStatus;
	
	private double orderTotalPrice;
	
	private List<CustomerOrderItemdto> customerOrderItem;
}
