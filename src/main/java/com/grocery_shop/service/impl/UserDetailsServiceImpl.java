package com.grocery_shop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.grocery_shop.api_response.ApiResponse;
import com.grocery_shop.dto.UserDetailDto;
import com.grocery_shop.entity.Role;
import com.grocery_shop.entity.UserDetail;
import com.grocery_shop.mappers.UserMapper;
import com.grocery_shop.repository.RoleRepository;
import com.grocery_shop.repository.UserDetailRepository;
import com.grocery_shop.service.UserDetailsService;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetailRepository detailRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Override
	@Transactional
	public ResponseEntity<?> addUser(UserDetailDto userDetailDto) {
		try {
			if(detailRepository.existsByEmailId(userDetailDto.getEmailId()))
				return ResponseEntity.ok(ApiResponse.responseError("User Already Exist", false));
			List<Role> roles = roleRepository.findByRoleIn(userDetailDto.getRole().stream().map(Role::getRole).toList());
			UserDetail userEntity = UserMapper.TO_USER_DETAIL_ENTITY(userDetailDto);
			userEntity.setRole(roles);
			userEntity.setPassword(getEncodedPassword(userEntity.getPassword()));
			userDetailDto = UserMapper.TO_USER_DTO(detailRepository.save(userEntity));
			return ResponseEntity.ok(ApiResponse.response(userDetailDto, "User Detail Saved Sucessfully", true));
		}catch(Exception e) {
			log.error(e.getMessage());
			e.printStackTrace();
			return ResponseEntity.ofNullable(ApiResponse.response("User Detail Not Saved", null, false));
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return detailRepository.findByEmailId(username);
	}
	
	private String getEncodedPassword(String password) {
		return new BCryptPasswordEncoder().encode(password);
	}

}
