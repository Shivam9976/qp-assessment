package com.grocery_shop.api_response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private boolean sucess;
	private T data;
	private String message;
	
	public static <T>ApiResponse<T> response(T data, String message, boolean sucess){
		return ApiResponse.<T>builder().data(data)
				.message(message).sucess(sucess).build();
	}
	public static <T>ApiResponse<T> response(T data){
		return ApiResponse.<T>builder().data(data)
				.build();
	}
	
	public static <T>ApiResponse<T> responseError(String message, boolean status){
		return ApiResponse.<T>builder().message(message)
				.sucess(status)
				.build();
	}
	
	public static <T> ApiResponse<T> empty() {
        return response(null, null,  false);
    }

	
}
