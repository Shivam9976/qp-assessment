package com.grocery_shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.grocery_shop.entity.UserDetail;
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

	UserDetail findByEmailId(String username);
	
	boolean existsByEmailId(String emailId);

}
