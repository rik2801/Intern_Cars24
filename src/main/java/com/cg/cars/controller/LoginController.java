package com.cg.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cars.entities.User;
import com.cg.cars.exception.LoginNotFoundException;
import com.cg.cars.exception.UserNotFoundException;
import com.cg.cars.service.ILoginService;


/*
 * Author : Sarath Sathyan
 * Version : 1.0
 * Date : 06-04-2021
 * Description : This is Login Controller
*/
@RestController
@RequestMapping("/api/cars")
public class LoginController {
	

		@Autowired
		private ILoginService loginService;
		
		@PatchMapping("/validatelogin")
		public ResponseEntity<String> validateLogin(@RequestBody User user) throws LoginNotFoundException,UserNotFoundException
		{
			ResponseEntity<String> loginResponse = new ResponseEntity<String>("User Id and Password Does Not Match", HttpStatus.ACCEPTED);
			if(!loginService.Login(user))
				throw new LoginNotFoundException("Login id  and Password Does not match");
			else
				loginResponse = new ResponseEntity<String>("Login Successful!", HttpStatus.ACCEPTED);
			return loginResponse;
		}
		
}
