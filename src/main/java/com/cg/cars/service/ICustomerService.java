package com.cg.cars.service;

import java.util.List;

import com.cg.cars.entities.Customer;
import com.cg.cars.exception.CustomerServiceException;
import com.cg.cars.model.CustomerDTO;

public interface ICustomerService {
	public CustomerDTO addCustomer(Customer customer) throws CustomerServiceException;

	public CustomerDTO removeCustomer(long custId) throws CustomerServiceException;

	public CustomerDTO updateCustomer(long id, Customer customer) throws CustomerServiceException;

	public CustomerDTO getCustomer(long custId) throws CustomerServiceException;

	public List<CustomerDTO> getAllCustomers() throws CustomerServiceException;

	public List<CustomerDTO> getCustomersByCity(String city) throws CustomerServiceException;

}
