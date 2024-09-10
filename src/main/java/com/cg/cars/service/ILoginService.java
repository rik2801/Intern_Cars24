package com.cg.cars.service;

import com.cg.cars.entities.User;
import com.cg.cars.exception.LoginNotFoundException;
import com.cg.cars.exception.UserNotFoundException;

public interface ILoginService {
	
	public boolean Login(User user) throws LoginNotFoundException, UserNotFoundException;

}
