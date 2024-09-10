package com.cg.cars.model;

import java.time.LocalDate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.stereotype.Component;

import com.cg.cars.entities.Car;
import com.cg.cars.entities.Customer;

@Component
public class CarDTO  {
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long carId;
	private String brand;
	private String model;
	private String variant;
	private LocalDate registrationYear;
	private String registrationState;
	private Customer customer;

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVariant() {
		return variant;
	}

	public void setVariant(String variant) {
		this.variant = variant;
	}

	public LocalDate getRegistrationYear() {
		return registrationYear;
	}

	public void setRegistrationYear(LocalDate registrationYear) {
		this.registrationYear = registrationYear;
	}

	public String getRegistrationState() {
		return registrationState;
	}

	@Override
	public String toString() {
		return "CarDTO [carId=" + carId + ", brand=" + brand + ", model=" + model + ", variant=" + variant
				+ ", registrationYear=" + registrationYear + ", registrationState=" + registrationState + ", customer="
				+ customer + "]";
	}

	public CarDTO(long carId, String brand, String model, String variant, LocalDate registrationYear,
			String registrationState, Customer customers) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.variant = variant;
		this.registrationYear = registrationYear;
		this.registrationState = registrationState;
		this.customer = customers;
	}

	public CarDTO() {
		super();

	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setRegistrationState(String registrationState) {
		this.registrationState = registrationState;
	}

	
}
