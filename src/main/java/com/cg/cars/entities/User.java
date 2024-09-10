package com.cg.cars.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "Users_Login")
public class User {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private long userId;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "role", nullable = false)
	private String role;

	public User() {
		super();
	}

	public User(long userId, String password, String role, String username) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", role=" + role + "]";
	}

}
