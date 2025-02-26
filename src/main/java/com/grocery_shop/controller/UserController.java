package com.grocery_shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery_shop.api_response.ApiResponse;
import com.grocery_shop.dto.UserDetailDto;
import com.grocery_shop.entity.UserDetail;
import com.grocery_shop.security_config.JWTUtil;
import com.grocery_shop.service.UserDetailsService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

	@Autowired
	UserDetailsService userDetailService;
	
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JWTUtil jwtUtil;
	
	@PostMapping("/addUser")
	public ResponseEntity<?> addUser(@Valid@RequestBody UserDetailDto userDetailDto){
		return userDetailService.addUser(userDetailDto);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestParam(name = "username"
	, required = false) String name, @RequestParam(name="password", required = false) String password, HttpSession httpSession){
		log.info("Hello Api Is Working");
		Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(name, password)
        );

        UserDetails user = (UserDetails) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);
		return ResponseEntity.ok(ApiResponse.response(token , "Login succesfull", true));
	}
}
