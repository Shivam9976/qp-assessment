package com.grocery_shop.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grocery_shop.api_response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("admin/")
@Slf4j
public class HelloController {

	@GetMapping("hello")
	public ResponseEntity<ApiResponse<String>> helloRequest(@RequestParam(name = "name", required = false) String name){
		return ResponseEntity.ok(ApiResponse.response("Hello " + name , "Hello Api is working", true));
	}
}
