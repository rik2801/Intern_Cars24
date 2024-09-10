package com.cg.cars.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

import com.cg.cars.entities.Address;

@Component
public class CustomerDTO  {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	private String name;
	private String email;
	private String contactNo;
	private LocalDate dob;
	private Address address;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CustomerDTO(int userId, String name, String email, String contactNo, LocalDate dob, Address add) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.contactNo = contactNo;
		this.dob = dob;
		this.address = add;
	}

	public CustomerDTO() {
		super();

	}

	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", name=" + name + ", email=" + email + ", contactNo=" + contactNo
				+ ", dob=" + dob + ", address=" + address + "]";
	}

	

	

}
