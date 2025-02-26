package com.grocery_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerOrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerOrderItemId;
	@ManyToOne
	@JoinColumn(name="order_id")
	private CustomerOrder order;
	
	private String itemName;
	@Column
	private long quantity;
	@Column
	private double price;
}
