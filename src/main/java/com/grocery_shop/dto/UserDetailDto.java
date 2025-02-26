package com.grocery_shop.dto;

import java.util.List;

import com.grocery_shop.entity.Role;
import com.grocery_shop.entity.UserAddress;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailDto {

	private Long userId;
	@NotBlank(message = "First Name Can Not Be Empty")
	private String firstName;
	private String lastName;
	@Email(message = "Email Is Not Valid")
	private String emailId;
	private String password;
	private List<Role> role;
	private UserAddress address;
}
