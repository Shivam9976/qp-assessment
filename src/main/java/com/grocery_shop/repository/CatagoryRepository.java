package com.grocery_shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery_shop.entity.Catagory;
@Repository
public interface CatagoryRepository extends JpaRepository<Catagory, Long> {

	public List<Catagory> findByCatagoryNameIn(List<String> catagoryNames);
}
