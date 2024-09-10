package com.cg.cars.utils;

import java.util.ArrayList;
import java.util.List;

import com.cg.cars.entities.Car;
import com.cg.cars.model.CarDTO;

public class CarUtils {
	public static List<CarDTO> convertToCarDtoList(List<Car> list) {
		List<CarDTO> CarDTOList = new ArrayList<CarDTO>();
		for (Car car : list)
			CarDTOList.add(convertToCarDto(car));
		return CarDTOList;
	}

	public static Car convertToCar(CarDTO CarDTO) {
		Car Car = new Car();

		Car.setCarId(CarDTO.getCarId());
		Car.setModel(CarDTO.getModel());
		Car.setBrand(CarDTO.getBrand());
		Car.setRegistrationState(CarDTO.getRegistrationState());
		Car.setRegistrationYear(CarDTO.getRegistrationYear());
		Car.setVariant(CarDTO.getVariant());
		Car.setCustomers(CarDTO.getCustomer());

		return Car;
	}

	public static CarDTO convertToCarDto(Car Car) {
		CarDTO CarDTO = new CarDTO();

		CarDTO.setCarId(Car.getCarId());
		CarDTO.setBrand(Car.getBrand());
		CarDTO.setModel(Car.getModel());
		CarDTO.setVariant(Car.getVariant());
		CarDTO.setRegistrationState(Car.getRegistrationState());
		CarDTO.setRegistrationYear(Car.getRegistrationYear());
		CarDTO.setCustomer(Car.getCustomers());
		return CarDTO;
	}

}
