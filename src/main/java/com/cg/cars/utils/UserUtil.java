package com.cg.cars.utils;

import java.util.ArrayList;
import java.util.List;

import com.cg.cars.entities.User;
import com.cg.cars.model.UserDTO;

public class UserUtil {
	
	public static List<UserDTO> convertToUserDtoList(List<User> list)
	{
		List<UserDTO> UserDTOList = new ArrayList<UserDTO>();
		for (User user : list)
			UserDTOList.add(convertToUserDto(user));
		return UserDTOList;
	}
	
	public static User convertToUser(UserDTO userDTO) {
		User user = new User();
		
		user.setUserId(user.getUserId());
		user.setPassword(user.getPassword());
		user.setRole(user.getRole());
		
		return user;
	}

	public static UserDTO convertToUserDto(User user) {
		UserDTO userDTO = new UserDTO();
		
		userDTO.setUserId(user.getUserId());

		userDTO.setusername(user.getUsername());
		userDTO.setPassword(user.getPassword());
		userDTO.setRole(user.getRole());
		
		return userDTO;
	}

}
