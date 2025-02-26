package com.grocery_shop.service;

import org.springframework.http.ResponseEntity;

import com.grocery_shop.dto.UserDetailDto;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

	public ResponseEntity<?> addUser(UserDetailDto userDetailDto);

}
