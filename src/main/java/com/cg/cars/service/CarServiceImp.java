package com.cg.cars.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cars.dao.ICarRepository;
import com.cg.cars.entities.Car;
import com.cg.cars.exception.CarServiceException;
import com.cg.cars.model.CarDTO;
import com.cg.cars.utils.CarUtils;

/**
*Author     :Shivam Dwivedi
*Date       :08-04-2021
*Description:This is  Car Service Class that 
*			 provide services to add a car, remove a car, update a car 
*            and to view car details
**/

@Service
public class CarServiceImp implements ICarService {

	@Autowired
	private ICarRepository carrepo;
	
	/**
	*Description	:To add Car to the database
	*Input Params	:Car object to be added to the database
	*Return Value	:CarDTO object
	*Exception	:CarServiceException-It is raised when car already exist   
	**/

	@Transactional
	@Override
	public CarDTO addCar(Car car)throws CarServiceException {
		Car addcar = new Car();
		addcar = carrepo.save(car);
		return CarUtils.convertToCarDto(addcar);
	}

	/**
	*Description	:To delete Car from the database
	*Input Params	:Car id to be deleted from the database
	*Return Value	:CarDTO object of the Car been deleted
	*Exception	:CarServiceException-It is raised when car ID doesn't exist   
	**/
	@Transactional
	@Override
	public CarDTO removeCar(long id) throws CarServiceException{
		Car car = new Car();
		car = carrepo.getOne(id);
		carrepo.deleteById((long) id);
		return CarUtils.convertToCarDto(car);
	}

	/**

	*Description	:To update Car details to the database
	*Input Params	:Car to be updated in the database
	*Return Value	:CarDTO object of the Car been updated
	*Exception	:CarServiceException-It is raised when car doesn't exist   
	**/
	@Transactional
	@Override
	public CarDTO updateCar(long id,Car car) throws CarServiceException{
		Car updcar =  carrepo.save(car);
		return CarUtils.convertToCarDto(updcar);
	}
	
	/**
	*Description	:To fetch Car details from the database
	*Input Params	:Car ID object to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car Id doesn't exist   
	**/

	@Transactional
	@Override
	public CarDTO getCar(long id) throws CarServiceException{
		Car getcar = new Car();
		getcar = carrepo.findById(id).orElse(null);
		return CarUtils.convertToCarDto(getcar);
	}
	
	/**
	*Description	:To fetch all Car details from the database
	*Return Value	:List<CarDTO> object of the Order been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	**/

	@Transactional
	@Override
	public List<CarDTO> getAllCars() throws CarServiceException{
		List<Car> getCar = new ArrayList<Car>();
		getCar = carrepo.findAll();
		return CarUtils.convertToCarDtoList(getCar);
	}

	/**
	*Description	:To fetch Car details based on model from the database
	*Input Params	:Car Model  to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	**/
	@Transactional
	@Override
	public List<CarDTO> getCarsByModel(String model) throws CarServiceException{
		return CarUtils.convertToCarDtoList(carrepo.findByModel(model));
	}

	/**
	*Description	:To fetch Car details based on brand from the database
	*Input Params	:Car brand  to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	**/
	
	@Transactional
	@Override
	public List<CarDTO> getCarsByBrand(String brand) throws CarServiceException{
		return CarUtils.convertToCarDtoList(carrepo.findByBrand(brand));
	}

	/**
	*Description	:To fetch Car details based on city from the database
	*Input Params	:Car brand  to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	**/
	
	@Transactional
	@Override
	public List<CarDTO> getCarsByLocation(String registrationState) throws CarServiceException{
		return CarUtils.convertToCarDtoList(carrepo.findByLocation(registrationState));
	}

}
