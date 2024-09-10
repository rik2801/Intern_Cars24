package com.cg.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cars.entities.Car;
import com.cg.cars.exception.CarServiceException;
import com.cg.cars.model.CarDTO;
import com.cg.cars.service.ICarService;

/**
*Author: Shivam Dwivedi
*Date:08-04-2021
*Description:This is Car Controller Layer
**/

@RestController
@RequestMapping("/api/showCar")
public class CarController {
	@Autowired
	private ICarService carService;
	
	/**
	*Description	:To add Car to the database
	*Input Params	:Car object to be added to the database
	*Return Value	:CarDTO object
	*Exception	:CarServiceException-It is raised when car already exist   
	 * @throws CarServiceException 
	**/

	@PostMapping(path = "/addCar", consumes = "application/json")
	public ResponseEntity<CarDTO> addCar(@RequestBody Car car) throws CarServiceException {
		CarDTO resultCar = carService.addCar(car);
		return new ResponseEntity<CarDTO>(resultCar, HttpStatus.OK);
	}

	/**
	*Description	:To delete Car from the database
	*Input Params	:Car id to be deleted from the database
	*Return Value	:CarDTO object of the Car been deleted
	*Exception	:CarServiceException-It is raised when car ID doesn't exist   
	 * @throws CarServiceException 
	**/
	
	@DeleteMapping(path = "/deleteCar/{carId}", produces = "application/json")

	public CarDTO removeCar(@PathVariable long carId) throws CarServiceException {
		return carService.removeCar(carId);
	}
	
	/**

	*Description	:To update Car details to the database
	*Input Params	:Car to be updated in the database
	*Return Value	:CarDTO object of the Car been updated
	*Exception	:CarServiceException-It is raised when car doesn't exist   
	 * @throws CarServiceException 
	**/

	@PutMapping("/updateCar/{id}")
	public ResponseEntity<CarDTO> updateCar(@RequestBody Car car) throws CarServiceException {

		CarDTO resultCar = carService.updateCar(car.getCarId(), car);
		return new ResponseEntity<CarDTO>(resultCar, HttpStatus.OK);

	}
	
	/**
	*Description	:To fetch Car details from the database
	*Input Params	:Car ID object to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car Id doesn't exist   
	 * @throws CarServiceException 
	**/

	@GetMapping(path = "/getCarDetails/{carId}", produces = "application/json")
	public ResponseEntity<CarDTO> getCarDetails(@PathVariable long carId) throws CarServiceException {
		CarDTO resultCar = carService.getCar(carId);

		return new ResponseEntity<CarDTO>(resultCar, HttpStatus.OK);

	}
	
	/**
	*Description	:To fetch all Car details from the database
	*Return Value	:List<CarDTO> object of the Order been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	 * @throws CarServiceException 
	**/

	@GetMapping(path = "/getAllCarDetails", produces = "application/json")
	public ResponseEntity<List<CarDTO> > getAllCars() throws CarServiceException {

		List<CarDTO> resultCar = carService.getAllCars();

		return new ResponseEntity<List<CarDTO> >(resultCar, HttpStatus.OK);

	}

	/**
	*Description	:To fetch Car details based on model from the database
	*Input Params	:Car Model  to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	 * @throws CarServiceException 
	**/
	
	@GetMapping("/getCarsByModel/{model}")
	public ResponseEntity <List<CarDTO> > getCarsByModel(@PathVariable String model) throws CarServiceException {
		return new ResponseEntity<List<CarDTO> > (carService.getCarsByModel(model),HttpStatus.OK); 
	}
	
	/**
	*Description	:To fetch Car details based on brand from the database
	*Input Params	:Car brand  to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	 * @throws CarServiceException 
	**/

	@GetMapping("/getCarsByBrand/{brand}")
	public ResponseEntity<List<CarDTO> > getCarsByBrand(@PathVariable String brand) throws CarServiceException {
		return new ResponseEntity<List<CarDTO> > (carService.getCarsByBrand(brand),HttpStatus.OK); 
	}
	
	/**
	*Description	:To fetch Car details based on Location from the database
	*Input Params	:Car brand  to be fetched from the database
	*Return Value	:CarDTO object of the Car been fetched
	*Exception	:CarServiceException-It is raised when car not found  
	 * @throws CarServiceException 
	**/
	
	@GetMapping("/getCarsByLocation/{registrationState}")
	public ResponseEntity<List<CarDTO> > getCarsByLocation(@PathVariable String registrationState) throws CarServiceException {
		return new ResponseEntity<List<CarDTO> > (carService.getCarsByLocation(registrationState),HttpStatus.OK); 
	}

}
