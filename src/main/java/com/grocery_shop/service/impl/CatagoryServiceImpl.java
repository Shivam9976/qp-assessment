package com.grocery_shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery_shop.entity.Catagory;
import com.grocery_shop.repository.CatagoryRepository;
import com.grocery_shop.service.CatagoryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CatagoryServiceImpl implements CatagoryService {

	private final CatagoryRepository catagoryRepository;
	
	@Override
	public List<Catagory> addCatagory(List<Catagory> catagories) {
		return catagoryRepository.saveAll(catagories);
	}

}
