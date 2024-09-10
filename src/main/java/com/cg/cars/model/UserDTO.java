package com.cg.cars.model;

import org.springframework.stereotype.Component;


@Component
public class UserDTO {

	private long userId;
	private String username;
	private String password;
	private String role;
	
	public long getUserId() {
		return userId;
	}
	
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getusername() {
		return username;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public UserDTO()
	{
		super();
	}
	
	public UserDTO(long userId, String username, String password, String role) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", username=" + username + ", password=" + password + ", role=" + role
				+ "]";
	}
}
