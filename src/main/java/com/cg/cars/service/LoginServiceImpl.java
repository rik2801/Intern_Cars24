package com.cg.cars.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cars.entities.User;
import com.cg.cars.exception.LoginNotFoundException;
import com.cg.cars.exception.UserNotFoundException;




@Service
public class LoginServiceImpl implements ILoginService {

	
	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean Login(User user) throws LoginNotFoundException,UserNotFoundException {
		boolean flag = false;
		if(user == null)
			throw new LoginNotFoundException("User Details cannot be Empty");
		else if(!userService.checkUser(user.getUserId(), user.getUsername(), user.getPassword()))
			throw new LoginNotFoundException("Invalid UserName or Password");
		else
			flag = true;
		return flag;
	}

}
