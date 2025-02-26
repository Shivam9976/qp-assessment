package com.grocery_shop.mappers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import com.grocery_shop.dto.UserDetailDto;
import com.grocery_shop.entity.UserDetail;

public class UserMapper {

//	@Autowired
//	private static ModelMapper modelMapper;
	
	public static UserDetailDto TO_USER_DTO(UserDetail userDetail){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDetail, UserDetailDto.class);
	}
	
	public static UserDetail TO_USER_DETAIL_ENTITY(UserDetailDto userDetailDto) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDetailDto, UserDetail.class);
	}
	
	public static List<UserDetailDto> TO_USER_DETAIL_DTO_COLLECTION (List<UserDetail> userDetails){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDetails, new TypeToken<List<UserDetailDto>>() {}.getType());
	}
	
	public static List<UserDetail> TO_USER_DETAIL_COLLECTION (List<UserDetailDto> userDetailDtos){
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(userDetailDtos, new TypeToken<List<UserDetail>>() {}.getType());
	}
	
	
}
