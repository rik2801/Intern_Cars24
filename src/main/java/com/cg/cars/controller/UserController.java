package com.cg.cars.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cars.entities.User;
import com.cg.cars.exception.UserNotFoundException;
import com.cg.cars.model.UserDTO;
import com.cg.cars.service.IUserService;


@RestController
@RequestMapping("/api/cars24")
public class UserController {
	
	@Autowired
	private IUserService userservice;

	@PostMapping("/addUser")
	public ResponseEntity<UserDTO> addUser(@RequestBody User user) {
		UserDTO resultuser = userservice.addUser(user);
		return new ResponseEntity<UserDTO>(resultuser, HttpStatus.OK);
	}

	@PutMapping("/updateUser")
	public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
		UserDTO resultuser = userservice.updateUser(user);
		return new ResponseEntity<UserDTO>(resultuser, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser/{userId}")
	public ResponseEntity<UserDTO> deleteUser(@PathVariable long userId) throws UserNotFoundException {
		UserDTO resultuser = userservice.deleteUser(userId);
		return new ResponseEntity<UserDTO>(resultuser, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{userId}")
	public ResponseEntity<UserDTO> getId(@PathVariable long userId) {
		UserDTO resultuser = userservice.getId(userId);
		return new ResponseEntity<UserDTO>(resultuser, HttpStatus.OK);
	}
	
}
