package com.grocery_shop.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.grocery_shop.entity.Role;
import com.grocery_shop.repository.RoleRepository;
import com.grocery_shop.service.RoleService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {

	private final RoleRepository repository;
	
	@Override
	public List<Role> addRoles(List<Role> roles) {
		return repository.saveAll(roles);
	}

}
