package com.cg.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cars.entities.Car;
import com.cg.cars.entities.Customer;
import com.cg.cars.exception.CarServiceException;
import com.cg.cars.model.CarDTO;
import com.cg.cars.service.CarServiceImp;
import com.cg.cars.utils.CarUtils;
@SpringBootTest
class CarServiceImpTest {

final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CarServiceImp service;
	

	@Test
	void testAddCar() throws CarServiceException  {
		Car c= new Car();
		c.setCarId(4);
		c.setBrand("Audi");
		c.setModel("R8");
		c.setVariant("High milege");
		c.setRegistrationYear(LocalDate.parse("2021-01-12"));
		c.setRegistrationState("Kerala");
		c.setCustomers(new Customer(67,"Avinash","avinash@gmail.com","9840712511",LocalDate.parse("2000-12-01"),"A2", "12 th", "Anna Nagar", "Chennai","Tamil Nadu",600012));
		Car addcar=CarUtils.convertToCar(service.addCar(c));
		c.setCarId(addcar.getCarId());
		c.setCustomers(addcar.getCustomers());
		assertEquals(c.getBrand(),service.addCar(c).getBrand());
		LOGGER.info("Add Executed");
	}
	
@Test
	void testRemoveCar() throws CarServiceException {
	assertEquals(service.getCar(63).getBrand(), service.removeCar(63).getBrand());
	LOGGER.info("Remove Excecuted");
	}

	@Test
	void testUpdateCar() throws CarServiceException {
		Car c= new Car();
		c.setCarId(65);
		c.setBrand("Audi");
		c.setModel("R8");
		c.setVariant("High milege");
		c.setRegistrationYear(LocalDate.parse("2021-01-12"));
		c.setRegistrationState("Kerala");
		c.setCustomers(new Customer(64,"Shivam","shivam@gmail.com","5258594173",LocalDate.parse("1999-12-01"),"15", "Rajesh nagar", "Alwarpett", "Chennai","Tamil Nadu",602024));
		assertEquals("Audi", service.updateCar(65, c).getBrand());
		LOGGER.info("Update Excecuted");
	}

	@Test
	void testGetCar() throws CarServiceException {
		CarDTO getCar=service.getCar(13);
		assertEquals("XL",service.getCar(13).getVariant());
		LOGGER.info("Get Car by ID Excecuted");
	}

	@Test
	void testGetCarsByModel() throws CarServiceException {
		assertEquals("Inova",service.getCar(13).getModel());
		LOGGER.info("Get Car by Model Excecuted");
	}

	@Test
	void testGetCarsByBrand() throws CarServiceException {
		assertEquals("Toyota",service.getCar(13).getBrand());
		LOGGER.info("Get Car by Brand Excecuted");
	}

	@Test
	void testGetCarsByLocation() throws CarServiceException {
		assertEquals("Andhra Pradesh",service.getCar(13).getRegistrationState());
		LOGGER.info("Get Car by Location Excecuted");
	}

}
