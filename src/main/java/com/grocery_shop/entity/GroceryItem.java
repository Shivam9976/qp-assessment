package com.grocery_shop.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class GroceryItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long itemId;
	@Column
	private String itemName;
	@Column
	private double price;
	@Column
	private Long quantity;
	@ManyToMany(fetch = FetchType.EAGER,  cascade = CascadeType.MERGE)
	@JoinTable(name="item_catagory", joinColumns=@JoinColumn(name="item_id")
	, inverseJoinColumns = @JoinColumn(name="catagory_id")
	)
	private List<Catagory> catagory;
}
