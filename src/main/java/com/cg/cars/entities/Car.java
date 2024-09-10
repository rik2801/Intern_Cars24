package com.cg.cars.entities;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Car")
public class Car  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long carId;

	@Column(name = "Brand", nullable = false, length = 50)
	private String brand;

	@Column(name = "Model", nullable = false, length = 50)
	private String model;

	@Column(name = "Variant", nullable = false, length = 50)
	private String variant;

	@Column(name = "REG_YR", nullable = false)
	private LocalDate registrationYear;

	@Column(name = "REG_State", nullable = false)
	private String registrationState;

	@ManyToOne(targetEntity = Customer.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "userId", referencedColumnName = "userid")
	private Customer customers;

	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getBrand() {
		return brand;
	}

	public Customer getCustomers() {
		return customers;
	}

	public void setCustomers(Customer customers) {
		this.customers = customers;
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

	public void setRegistrationState(String registrationState) {
		this.registrationState = registrationState;
	}

	public Car() {
		super();

	}

	public Car(long carId, String brand, String model, String variant, LocalDate registrationYear,
			String registrationState, Customer customers) {
		super();
		this.carId = carId;
		this.brand = brand;
		this.model = model;
		this.variant = variant;
		this.registrationYear = registrationYear;
		this.registrationState = registrationState;
		this.customers = customers;
	}

	@Override
	public String toString() {
		return "Car [carId=" + carId + ", brand=" + brand + ", model=" + model + ", variant=" + variant
				+ ", registrationYear=" + registrationYear + ", registrationState=" + registrationState + ", customers="
				+ customers + "]";
	}

	

	}
