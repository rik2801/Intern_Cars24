package com.cg.cars.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cars.entities.Customer;
import com.cg.cars.exception.CustomerServiceException;
import com.cg.cars.model.CustomerDTO;
import com.cg.cars.service.CustomerServiceImp;
import com.cg.cars.service.ICustomerService;

/**
 * Author : Monisha 
 * Date : 08-04-2021 
 * Description : This is Customer Controller Layer
 **/

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ICustomerService customerService;

	/**
	 * Description : To add the customer to the database 
	 * Input params : customer object to be added to the database 
	 * Return Value : CustomerDTO object
	 * Exception : CustomerServiceException - It is raised when customer already exists
	 **/

	@PostMapping(path = "/addcustomer", consumes = "application/json")
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Object> insertCustomer(@RequestBody Customer customer) {
			CustomerDTO resultcustomer;
			try {
				resultcustomer = customerService.addCustomer(customer);
				return new ResponseEntity<Object>(resultcustomer, HttpStatus.OK);
				
			}catch (CustomerServiceException e) {
					return new ResponseEntity<Object>(e.toString(), HttpStatus.BAD_REQUEST);
				}
	}
			
		

	/**
	 * Description : To delete the customer to the database 
	 * Input params : customer Id to be deleted from the database 
	 * Return Value : CustomerDTO object of the customer been deleted 
	 * Exception : CustomerServiceException - It is raised when customer ID doesn't exists
	 * @throws CustomerServiceException 
	 **/

	@DeleteMapping(path = "/deletecustomer/{id}", produces = "application/json")
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<CustomerDTO> deleteCustomer(@PathVariable long id) throws CustomerServiceException  {
		CustomerDTO resultcustomer = customerService.removeCustomer(id);
		return new ResponseEntity<CustomerDTO>(resultcustomer, HttpStatus.OK);

	}

	/**
	 * Description : To update the customer details to the database 
	 * Input params :customer to be updated in the database 
	 * Return Value : CustomerDTO object of the customer been updated 
	 * Exception : CustomerServiceException - It is raised when customer doesn't exists
	 * @throws CustomerServiceException 
	 **/

	@PutMapping(path = "/updatecustomer/{id}", produces = "application/json")
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody Customer customer) throws CustomerServiceException  {
		if (CustomerServiceImp.validateUserContact(customer) && CustomerServiceImp.validateUserMail(customer)
				&& CustomerServiceImp.validateUserName(customer)) {

			CustomerDTO resultCustomer = customerService.updateCustomer(customer.getUserId(), customer);
			return new ResponseEntity<CustomerDTO>(resultCustomer, HttpStatus.OK);
		}
		throw new CustomerServiceException("Enter Valid Customer details");
	}

	/**
	 * Description : To fetch the particular customer detail from the database 
	 * Input params : Customer Id to be fetched from the database 
	 * Return Value :CustomerDTO object of the customer been fetched Exception :
	 * CustomerServiceException - It is raised when customer Id doesn't exists
	 * @throws CustomerServiceException 
	 **/

	@GetMapping("/getcustomer/{id}")
	@ExceptionHandler(value = CustomerServiceException.class)
	public ResponseEntity<CustomerDTO> getCustomer(@PathVariable int id) throws CustomerServiceException  {
		if (id != 0 && id <= 500) {
			CustomerDTO resultCustomer = customerService.getCustomer(id);
			return new ResponseEntity<CustomerDTO>(resultCustomer, HttpStatus.OK);
		}
		throw new CustomerServiceException("ID cannot be empty or greater than 500");
	}

	/**
	 * Description : To fetch all Customer details from the database 
	 * Return Value :List<CustomerDTO> object of the customers been fetched
	 *  Exception : CustomerServiceException - It is raised when Customer not found
	 * @throws CustomerServiceException 
	 **/

	@GetMapping(path = "/allcustomers", produces = "application/json")
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<List<CustomerDTO> > getAllCustomer() throws CustomerServiceException {
		    List<CustomerDTO> resultCustomer;
			resultCustomer = customerService.getAllCustomers();
			return new ResponseEntity<List<CustomerDTO> >(resultCustomer, HttpStatus.OK);
	}

	/**
	 * Description : To fetch the Customer details belongs to particular City from the database
	 * Input params : City which details to be fetched from the database 
	 * Return Value : List<CustomerDTO> object of the customers been fetched 
	 * Exception : CustomerServiceException - It is raised when Customer not found
	 * @throws CustomerServiceException 
	 **/

	@GetMapping("/getcustomerCity/{city}")
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<List<CustomerDTO> > getCustomerbyCity(@PathVariable String city) throws CustomerServiceException {
		List<CustomerDTO> resultCustomer = customerService.getCustomersByCity(city);
		return new ResponseEntity<List<CustomerDTO> >(resultCustomer, HttpStatus.OK);
	}

}
