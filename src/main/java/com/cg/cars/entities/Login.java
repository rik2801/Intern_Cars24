package com.cg.cars.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Login")
public class Login {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "LOGIN_ID")
	private int loginId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "userId")
	private User user;

	public Login() {
		super();
	}

	public Login(int loginId, User user) {
		super();
		this.loginId = loginId;
		this.user = user;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Login [loginId=" + loginId + ", user=" + user + "]";
	}
	
}
