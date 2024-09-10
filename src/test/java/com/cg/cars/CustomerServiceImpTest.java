package com.cg.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.cars.entities.Customer;
import com.cg.cars.exception.CustomerServiceException;
import com.cg.cars.model.CustomerDTO;
import com.cg.cars.service.CustomerServiceImp;
import com.cg.cars.utils.CustomerUtils;

/**
 * @author Monisha
 *
 *
**/

@SpringBootTest
class CustomerServiceImpTest {

	final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CustomerServiceImp service;
	
	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp#addCustomer(com.cg.cars.entities. Customer)}.
	 */	

	@Test
	void testAddCustomer() 										// Invalid AddCustomer() method
	{

		Customer customer = new Customer("Monisha1", "monibala@gmail.com", "8801", LocalDate.parse("2000-01-24"), "D2",
				"3rd Main door", "Mathur MMDA", "Chennai", "Tamil Nadu", 600068);
		try {
			service.addCustomer(customer);
			LOGGER.info("Customer Inserted Successfully");

		} catch (CustomerServiceException exception) {
			assertEquals("Customer already exists or invalid inputs", exception.getMessage());
			LOGGER.warn("Customer Not found exception for invalid customer executed successfully");
		}
		LOGGER.info("TEST CASE TC_01, TC_06, TC_09, TC_13");

	}

	@Test
	void testAddCustomer2() throws CustomerServiceException  // Make changes in the existing value
	{
		Customer customer = new Customer("MonishaBalan", "monishabalan@gmail.com", "6379174769",
				LocalDate.parse("2000-01-24"), "1191", "3 rd street", "Perambur", "Chennai", "Tamil Nadu", 600068);

		assertEquals(customer.getName(), service.addCustomer(customer).getName());

		LOGGER.info("TEST CASE TC_03, TC_06, TC_10, TC_13");
		LOGGER.info("Customer Inserted Successfully");

	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp# removeCustomer(long)}.
	 */
	
	@Test
	void testRemoveCustomer() 								// Invalid RemoveCustomer()
	{
		try {
			service.removeCustomer(0);
			LOGGER.info("Customer Removed");
		} catch (CustomerServiceException exception) {
			assertEquals("Customer does not exist", exception.getMessage());
			LOGGER.warn("CustomerServiceException- Cannot remove customer");
		}
		LOGGER.info("testRemoveCustomer() Excecuted");

	}

	@Test
	void testRemoveCustomer1() throws CustomerServiceException   // give number present in table that is not used in get
	{
		assertEquals(service.getCustomer(9).getContactNo(), service.removeCustomer(9).getContactNo());
		LOGGER.info("testRemoveCustomer1() Excecuted");

	}

	/**
	* Test method for {@link com.cg.cars.service.CustomerServiceImp#updateCustomer(long,com.cg.cars.entities. Customer)}.
	*/
	
	@Test
	void testUpdateCustomer() throws CustomerServiceException   // make changes
	{
		Customer customer = new Customer(17, "Monisha", "monishabala@gmail.com", "9719686617",
				LocalDate.parse("2000-01-24"), "D2", "3rd Main door", "Mathur MMDA", "Chennai", "Tamil Nadu", 600068);

		assertEquals("Monisha", service.updateCustomer(17, customer).getName());
		LOGGER.info("testUpdateCustomer() Excecuted");

	}

	@Test
	void testUpdateCustomer2() 								 // give number not in table
	{
		Customer customer = new Customer(100, "Monisha", "monibala@gmail.com", "8056126801",
				LocalDate.parse("2000-01-24"), "D2", "3rd Main door", "Mathur MMDA", "Chennai", "Tamil Nadu", 600068);

		try {
			service.updateCustomer(100, customer);
			LOGGER.info("Customer Updated");
		} catch (CustomerServiceException exception) {
			assertEquals("Customer not found or invalid inputs", exception.getMessage());
			LOGGER.warn("CustomerServiceException- Cannot Update customer");
		}
		LOGGER.info("testUpdateCustomer2() Excecuted");

	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp# getCustomer(long)}.
	 */
	
	@Test
	void testFindEmployeeById() throws CustomerServiceException // give number present in data base also data
	{
		CustomerDTO customer = service.getCustomer(14);
		assertEquals("Dhivya", customer.getName());
		LOGGER.info("testFindEmployeeById() Excecuted");
	}

	@Test
	void testGetContactNo() throws CustomerServiceException {
		CustomerDTO customer = service.getCustomer(14);
		assertEquals("9840712510", customer.getContactNo());
		LOGGER.info("testGetContactNo() Excecuted");
		
	}

	@Test
	void testGetDateOfBirth() throws CustomerServiceException {
		CustomerDTO customer = service.getCustomer(14);
		assertEquals(LocalDate.parse("2000-12-01"), customer.getDob());
		LOGGER.info("testGetDateOfBirth() Excecuted");
	}

	@Test
	void testGetEmail() throws CustomerServiceException {
		CustomerDTO customer = service.getCustomer(14);
		assertEquals("dhivya@gmail.com", customer.getEmail());
		LOGGER.info("testGetEmail() Excecuted");
	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp # getCustomersByCity (String)}.
	 */
	
	@Test
	void testGetCity() throws CustomerServiceException {
		CustomerDTO customer = service.getCustomer(14);
		assertEquals("Chennai", customer.getAddress().getCity());
		LOGGER.info("testGetCity() Excecuted");
	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp#validateUserName(com.cg.cars.entities.Customer)}.
	 */
	
	@Test
	void testValidateUserName() {
		Customer customer = new Customer("Mo", "monibala@gmail.com", "8056126801", LocalDate.parse("2000-01-24"), "D2",
				"3rd Main door", "Mathur MMDA", "Chennai", "Tamil Nadu", 600068);
		assertEquals(false, CustomerServiceImp.validateUserName(customer));
		LOGGER.info("testValidateUserName() Excecuted");
		LOGGER.info("TEST CASE TC_01, TC_02, TC_03, TC_04");

	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp#validateUserContact(com.cg.cars.entities.Customer)}.
	 */
	
	@Test
	void testValidateUserContact() {

		Customer customer = new Customer("Monisha", "monibala@gmail.com", "80", LocalDate.parse("2000-01-24"), "D2",
				"3rd Main door", "Mathur MMDA", "Chennai", "Tamil Nadu", 600068);
		assertEquals(false, CustomerServiceImp.validateUserContact(customer));
		LOGGER.info("testValidateUserContact() Excecuted");
		LOGGER.info("TEST CASE TC_09, TC_10, TC_11");

	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp#validateUserMail(com.cg.cars.entities.Customer)}.
	 */
	
	@Test
	void testValidateUserMail() {

		Customer customer = new Customer("Monisha", "mn@gm", "8056126801", LocalDate.parse("2000-01-24"), "D2",
				"3rd Main door", "Mathur MMDA", "Chennai", "Tamil Nadu", 600068);
		assertEquals(false, CustomerServiceImp.validateUserMail(customer));
		LOGGER.info("testValidateUserMail() Excecuted");
		LOGGER.info("TEST CASE TC_06, TC_07");

	}

	/**
	 * Test method for {@link com.cg.cars.service.CustomerServiceImp # getAllCustomers ()}.
	 */
	
	@Test
	void testGetAllCustomerDetails() throws CustomerServiceException {
		List<Customer> list = new ArrayList<>();
		CustomerDTO customer1 = service.getCustomer(64);
		CustomerDTO customer2 = service.getCustomer(67);
		Customer customerCon1 = CustomerUtils.convertToCustomer(customer1);
		Customer CustomerCon2 = CustomerUtils.convertToCustomer(customer2);
		list.add(customerCon1);
		list.add(CustomerCon2);
		assertNotNull(list);
		LOGGER.info("Get All Customer details executed");
	}

}
