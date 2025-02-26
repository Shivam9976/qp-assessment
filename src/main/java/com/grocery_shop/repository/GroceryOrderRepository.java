package com.grocery_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grocery_shop.entity.CustomerOrder;
@Repository
public interface GroceryOrderRepository extends JpaRepository<CustomerOrder, Long>{

}
