package com.grocery_shop;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import com.grocery_shop.entity.Catagory;
import com.grocery_shop.entity.Role;
import com.grocery_shop.repository.CatagoryRepository;
import com.grocery_shop.repository.RoleRepository;



@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class GroceryShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroceryShopApplication.class, args);
	}
	
//	@Bean
//	public CommandLineRunner commandLineRunner(RoleRepository repository, CatagoryRepository catagoryRepository) {
//		List<Role> roles = List.of(Role.builder().role("Admin").build(), Role.builder().role("User").build());
//		List<Catagory> catagories = List.of(Catagory.builder().catagoryName("Fruit").build(), Catagory.builder().catagoryName("Vegetable").build());
//		repository.saveAll(roles);
//		return args -> catagoryRepository.saveAll(catagories);
//	}
//	
//	@Bean
//	public ModelMapper modelMapper() {
//		return new ModelMapper();
//	}

}
