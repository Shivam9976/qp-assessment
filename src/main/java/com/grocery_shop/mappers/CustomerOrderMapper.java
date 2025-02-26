package com.grocery_shop.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.grocery_shop.dto.CustomerOrderDto;
import com.grocery_shop.entity.CustomerOrder;

public class CustomerOrderMapper {

	
	public static CustomerOrderDto TO_CUSTOMER_ORDER_DTO(CustomerOrder customerOrder) {
		return new ModelMapper().map(customerOrder, CustomerOrderDto.class);
	}
	
	public static CustomerOrder TO_CUSTOMER_ORDER_ENTITY(CustomerOrderDto customerOrderDto) {
		return new ModelMapper().map(customerOrderDto, CustomerOrder.class);
	}
	
	public static List<CustomerOrderDto> TO_CUSTOMER_ORDER_DTO_COLLECTION(List<CustomerOrder> customerOrders){
		return new ModelMapper().map(customerOrders, new TypeToken<List<CustomerOrderDto>>() {} .getType());
	}
	
	public static List<CustomerOrder> TO_CUSTOMER_ORDER_ENTITY_COLLECTION(List<CustomerOrderDto> customerOrders){
		return new ModelMapper().map(customerOrders, new TypeToken<List<CustomerOrder>>() {} .getType());
	}
}
