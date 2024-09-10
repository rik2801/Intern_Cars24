package com.cg.cars.service;

import java.util.List;

import com.cg.cars.entities.Car;
import com.cg.cars.exception.CarServiceException;
import com.cg.cars.model.CarDTO;

public interface ICarService {
	public CarDTO addCar(Car car) throws CarServiceException;

	public CarDTO removeCar(long id) throws CarServiceException;

	public CarDTO updateCar(long id, Car car)throws CarServiceException;

	public CarDTO getCar(long id) throws CarServiceException;

	public List<CarDTO> getAllCars() throws CarServiceException;

	public List<CarDTO> getCarsByModel(String model) throws CarServiceException;

	public List<CarDTO> getCarsByBrand(String brand)  throws CarServiceException;
	
	public List<CarDTO> getCarsByLocation(String registrationState) throws CarServiceException;

}
