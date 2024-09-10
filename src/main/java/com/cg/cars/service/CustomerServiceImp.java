package com.cg.cars.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cars.dao.ICustomerRepository;
import com.cg.cars.entities.Customer;
import com.cg.cars.exception.CustomerServiceException;
import com.cg.cars.model.CustomerDTO;
import com.cg.cars.utils.CustomerUtils;

/**
*Author      : Monisha V
*Date        :12-04-2021
*Description : This is Customer Service Class that provides the
 *   		   services to add a customer, remove a customer, update a customer and
 *             view a customer
**/

@Service
public class CustomerServiceImp implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepo;

	/**
	 * Description : To add the customer to the database 
	 * Input params : Customer object to be added to the database 
	 * Return Value : CustomerDTO object
	 * Exception : CustomerServiceException - It is raised when customer already exists
	 * @throws CustomerServiceException 
	 **/

	@Transactional
	@Override
	public CustomerDTO addCustomer(Customer customer) throws CustomerServiceException {
		Optional<Customer> addCustomerTemp = customerRepo.findById(customer.getUserId());
		if (addCustomerTemp.isEmpty() && CustomerServiceImp.isValidCustomer(customer)
				&& CustomerServiceImp.validateUserContact(customer) && CustomerServiceImp.validateUserMail(customer)) {
			Customer addCustomer = customerRepo.save(customer);
			return CustomerUtils.convertToCustomerDto(addCustomer);
		} else {
			throw new CustomerServiceException("Customer already exists or invalid inputs");
		}
	}

	/**
	 * Description : To delete the customer to the database 
	 * Input params : customer Id to be deleted from the database 
	 * Return Value : CustomerDTO object of the customer been deleted 
	 * Exception : CustomerServiceException - It is raised when customer ID doesn't exists
	 * @throws CustomerServiceException
	 **/
	@Transactional
	@Override
	public CustomerDTO removeCustomer(long custId) throws CustomerServiceException{

		Optional<Customer> customerTemp = customerRepo.findById(custId);
		if (customerTemp.isEmpty()) {
			throw new CustomerServiceException("Customer does not exist");
		} else {
			customerRepo.deleteById(custId);
			if (customerTemp.isPresent()) {
				return CustomerUtils.convertToCustomerDto(customerTemp.get());
			} else {
				throw new CustomerServiceException("Customer is not present");
			}

		}
	}
	
	/**
	 * Description : To update the customer details to the database 
	 * Input params :customer to be updated in the database 
	 * Return Value : CustomerDTO object of the customer been updated 
	 * Exception : CustomerServiceException - It is raised when customer doesn't exists
	 * @throws CustomerServiceException
	 **/

	@Transactional
	@Override
	public CustomerDTO updateCustomer(long id,Customer customer)throws CustomerServiceException {
		Optional<Customer> updateCustomerTemp = customerRepo.findById(customer.getUserId());
		if (updateCustomerTemp.isEmpty() && CustomerServiceImp.validateUserName(customer)
				&& CustomerServiceImp.validateUserContact(customer) && CustomerServiceImp.validateUserMail(customer)) {
			throw new CustomerServiceException("Customer not found or invalid inputs");
		} else {
			Customer updateCustomer = customerRepo.save(customer);
			return CustomerUtils.convertToCustomerDto(updateCustomer);
		}
	}

	/**
	 * Description : To fetch the particular customer detail from the database 
	 * Input params : Customer Id to be fetched from the database 
	 * Return Value :CustomerDTO object of the customer been fetched Exception :
	 * CustomerServiceException - It is raised when customer Id doesn't exists
	 * @throws CustomerServiceException
	 **/
	
	@Transactional
	@Override
	public CustomerDTO getCustomer(long custId)throws CustomerServiceException {
		Optional<Customer> getCustomerTemp = customerRepo.findById(custId);
		if (getCustomerTemp.isEmpty()) {
			throw new CustomerServiceException("Customer does not exist");
		} else {
			Customer getCustomer = customerRepo.findById(custId).orElse(null);
			return CustomerUtils.convertToCustomerDto(getCustomer);

		}
	}

	/**
	 * Description : To fetch all Customer details from the database 
	 * Return Value :List<CustomerDTO> object of the customers been fetched
	 *  Exception : CustomerServiceException - It is raised when Customer not found
	 *  @throws CustomerServiceException
	 **/
	
	@Transactional
	@Override
	public List<CustomerDTO> getAllCustomers() throws CustomerServiceException{
		List<Customer> getCustomer = new ArrayList<Customer>();
		getCustomer = customerRepo.findAll();
		return CustomerUtils.convertToCustomerDtoList(getCustomer);

	}

	/**
	 * Description : To fetch the Customer details belongs to particular City from the database
	 * Input params : City which details to be fetched from the database 
	 * Return Value : List<CustomerDTO> object of the customers been fetched 
	 * Exception : CustomerServiceException - It is raised when Customer not found
	 * @throws CustomerServiceException
	 **/
	
	@Transactional
	@Override
	public List<CustomerDTO> getCustomersByCity(String city)throws CustomerServiceException {
		List<Customer> getCustomer = new ArrayList<Customer>();
		getCustomer = customerRepo.findByCity(city);
		if (getCustomer.isEmpty()) {
			throw new CustomerServiceException("Customers not found");
		} else {
			return CustomerUtils.convertToCustomerDtoList(getCustomer);
		}

	}

	public static boolean isValidCustomer(Customer cus) {
		return validateUserMail(cus) && validateUserName(cus) && validateUserContact(cus);
	}

	public static boolean validateUserName(Customer customer) {
		boolean flag = true;
		if (customer.getName().length() < 3 || customer.getName().length() > 20 || customer.getName().isEmpty()
				|| !(customer.getName().matches("^[a-zA-Z]*$"))) {
			flag = false;
		}
		return flag;
	}

	public static boolean validateUserContact(Customer customer) {
		boolean flag = true;
		if (customer.getContactNo().length() != 10 || customer.getContactNo().isEmpty()|| 
				!(customer.getContactNo().matches("^\\d{10}$"))) {
			flag = false;
		}
		return flag;
	}

	public static boolean validateUserMail(Customer customer) {
		boolean flag = true;
		if (customer.getEmail().length() < 8 || customer.getEmail().length() > 30 || customer.getEmail().isEmpty()
				|| !(customer.getEmail().matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$"))) {
			flag = false;
		}
		return flag;
	}}
