package com.cg.cars.service;

import com.cg.cars.entities.User;
import com.cg.cars.exception.UserNotFoundException;
import com.cg.cars.model.UserDTO;

public interface IUserService {

	public UserDTO addUser(User user);

	public UserDTO updateUser(User user);

	public UserDTO deleteUser(long userId) throws UserNotFoundException;

	public UserDTO getId(long userId);


}




