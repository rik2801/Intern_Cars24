package com.cg.cars;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.cg.cars.entities.Address;
import com.cg.cars.entities.Appointment;
import com.cg.cars.entities.Customer;
import com.cg.cars.entities.Payment;
import com.cg.cars.exception.AppointmentExceptions;
import com.cg.cars.exception.AppoitnmentNotFoundException;
import com.cg.cars.exception.InvalidAppointmentDateException;
import com.cg.cars.exception.InvalidAppointmentInspectionTypeException;
import com.cg.cars.exception.InvalidAppointmentLocationException;
import com.cg.cars.exception.InvalidAppointmentTimeException;
import com.cg.cars.model.AppointmentDTO;
import com.cg.cars.model.CustomerDTO;
import com.cg.cars.model.PaymentDTO;
import com.cg.cars.service.AppointmentServiceImpl;
import com.cg.cars.service.IAppointmentService;
import com.cg.cars.service.ICustomerService;
import com.cg.cars.service.IPaymentService;
import com.cg.cars.utils.AppointmentUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author vivek
 *
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AppointmentServiceImplTest {

	@Autowired
	private IAppointmentService appointmentService;

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IPaymentService paymentService;

	private static List<AppointmentDTO> appointments;
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	@BeforeAll
	static void initialize()
	{
		appointments = new ArrayList<>(); 
	}
	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#addAppointment(com.cg.cars.entities.Appointment)}.
	 */
	@Test
	@Order(1)
	void testAddAppointmentWithPayment() {
		
		Address address=new Address("12", "street", "Area", "Chennai", "Tamil Nadu", 123456);
		Customer customer=new Customer("TestName", "testemail@email.com", "1234567890", LocalDate.of(2000, 1, 1), address);
		LocalDate expiryDate = LocalDate.parse("2025-05-24");
		Payment payment = new Payment(101, "CreditCard", "Success", 1, "avinash", "3258147036971236", expiryDate, 112);
		Appointment appointment = new Appointment(1, "Chennai", "PAY1", LocalDate.now(), LocalTime.of(15, 0,0), customer, payment);

		CustomerDTO customerDTO;

		PaymentDTO paymentDTO;

		AppointmentDTO appointmentDTO;

		try
		{
			LOGGER.info("TEST CASE ID TC_33");
			customerDTO = customerService.addCustomer(customer);
			customer.setUserId(customerDTO.getUserId());

			paymentDTO = paymentService.addPayment(payment);
			payment.setCard(paymentDTO.getCard());
			payment.setPaymentId(paymentDTO.getPaymentId());

			appointmentDTO = appointmentService.addAppointment(appointment);
			appointment.setAppointmentId(appointmentDTO.getAppointmentId());

			AppointmentDTO original = AppointmentUtils.convertToAppointmentDTO(appointment);
			boolean equals=false;
			
			equals = original.getAppointmentId() == appointmentDTO.getAppointmentId()
						&& original.getInspectionType().equals(appointmentDTO.getInspectionType())
						&& original.getLocation().equals(appointmentDTO.getLocation())
						&& original.getPreferredDate().equals(appointmentDTO.getPreferredDate())
						&& original.getPreferredTime().equals(appointmentDTO.getPreferredTime());

			System.out.println(appointments.add(appointmentDTO));
			assertTrue(equals);

			LOGGER.info("Appointment with payment test case passed successfully");
		}
		catch(Exception e)
		{
			fail("Adding appointment failed");
			LOGGER.info("Appointment with payment test case failed with error message of "+ e.getMessage());
		}

	}

	@Test
	@Order(2)
	void testAddAppointmentWithoutPayment() {
		
		Address address=new Address("12", "street", "Area", "Chennai", "Tamil Nadu", 123456);
		Customer customer=new Customer("TestName", "testemail2@email.com", "2334567890", LocalDate.of(2000, 1, 1), address);
		Appointment appointment = new Appointment(1, "Chennai", "CHK1", LocalDate.now(), LocalTime.of(15, 0,0), customer, null);

		CustomerDTO customerDTO;

		AppointmentDTO appointmentDTO;

		try
		{
			LOGGER.info("TEST CASE ID TC_34");
			customerDTO = customerService.addCustomer(customer);
			customer.setUserId(customerDTO.getUserId());

			appointmentDTO = appointmentService.addAppointment(appointment);
			appointment.setAppointmentId(appointmentDTO.getAppointmentId());

			AppointmentDTO original = AppointmentUtils.convertToAppointmentDTO(appointment);
			boolean equals=false;
			
			equals = original.getAppointmentId() == appointmentDTO.getAppointmentId()
						&& original.getInspectionType().equals(appointmentDTO.getInspectionType())
						&& original.getLocation().equals(appointmentDTO.getLocation())
						&& original.getPreferredDate().equals(appointmentDTO.getPreferredDate())
						&& original.getPreferredTime().equals(appointmentDTO.getPreferredTime());

			
			System.out.println(appointments.add(appointmentDTO));
			assertTrue(equals);

			LOGGER.info("Appointment without payment test case passed successfully");
		}
		catch(Exception e)
		{
			fail("Adding appointment failed");
			LOGGER.info("Appointment with payment test case failed with error message of "+ e.getMessage());
		}

	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#getAppointment(int)}.
	 */
	@Order(3)
	@Test
	void testGetAppointment() {
		
		LOGGER.info("TEST CASE TC_35");
		AppointmentDTO reference = appointments.get(1);
		try {
			AppointmentDTO appointmentDTO =  appointmentService.getAppointment(reference.getAppointmentId());
			boolean equals=false;
			
			equals = reference.getAppointmentId() == appointmentDTO.getAppointmentId()
						&& reference.getInspectionType().equals(appointmentDTO.getInspectionType())
						&& reference.getLocation().equals(appointmentDTO.getLocation())
						&& reference.getPreferredDate().equals(appointmentDTO.getPreferredDate())
						&& reference.getPreferredTime().equals(appointmentDTO.getPreferredTime());

			assertTrue(equals);

			LOGGER.info("Appointment get is successful");

		} catch (AppoitnmentNotFoundException e) {
			LOGGER.error("Appointment with ID "+ reference.getAppointmentId()+" not found" +  e.getClass());
		}
	}

	@Order(4)
	@Test
	void testAppointmentNotFoundExceptionForGetAppointment()
	{
		LOGGER.info("TEST CASE TC_36");
		assertThrows(AppoitnmentNotFoundException.class,()-> appointmentService.getAppointment(150));
		LOGGER.info("Appointment Not found exception for invalid appointment ID executed successfully");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#getAllAppointments()}.
	 */
	@Order(5)
	@Test
	void testGetAllAppointments() {
		
		LOGGER.info(("TEST CASE TC_37"));
		List<AppointmentDTO> getList=appointmentService.getAllAppointments();
		appointments.sort(null);
		getList.sort(null);
		assertIterableEquals(getList, appointments);
		LOGGER.info("Getting all appointments test case executed");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#getOpenAppointments()}.
	 */
	@Order(6)
	@Test
	void testGetOpenAppointments() {
		LOGGER.info(("TEST CASE TC_38"));
		List<AppointmentDTO> getList=appointmentService.getAllAppointments();
		appointments.sort(null);
		getList.sort(null);
		assertIterableEquals(getList, appointments);
		LOGGER.info("Getting all open appointments test case executed");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#isValidAppointment(com.cg.cars.entities.Appointment)}.
	 */
	@Order(7)
	@Test
	void testIsValidAppointment() {

		LOGGER.info(("TEST CASE TC_39"));

		Address address=new Address("12", "street", "Area", "Chennai", "Tamil Nadu", 123456);
		Customer customer=new Customer("TestName", "testemail2@email.com", "2334567890", LocalDate.of(2000, 1, 1), address);
		Appointment appointment = new Appointment(1, "Chennai", "CHK1", LocalDate.now(), LocalTime.of(15, 0,0), customer, null);

		try {
			assertTrue(AppointmentServiceImpl.isValidAppointment(appointment));
			LOGGER.info("Appointment is in valid format");
			
		} catch (AppointmentExceptions e) {
			LOGGER.error("Appointment is invalid due to "+ e.getMessage() , e.getClass());
		}
		
	}

	@Order(8)
	@Test
	void testIsValidAppointmentInvalidData() {
		
		LOGGER.info(("TEST CASE TC_40"));

		Address address=new Address("12", "street", "Area", "Chennai", "Tamil Nadu", 123456);
		Customer customer=new Customer("TestName", "testemail2@email.com", "2334567890", LocalDate.of(2000, 1, 1), address);
		Appointment appointment = new Appointment(1, "Chennai", "CHK1", LocalDate.now(), LocalTime.of(0, 0,0), customer, null);

		assertThrows(AppointmentExceptions.class, ()->AppointmentServiceImpl.isValidAppointment(appointment));

		LOGGER.info("Appointment is invalid format");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#isValidAppointmentDate(java.time.LocalDate)}.
	 */
	@Order(9)
	@Test
	void testIsValidAppointmentDate() {
		LOGGER.info(("TEST CASE TC_41"));

		try
		{
			assertTrue(AppointmentServiceImpl.isValidAppointmentDate(LocalDate.now()));
			LOGGER.info("Appointment date is valid");
		}
		catch(InvalidAppointmentDateException e)
		{
			LOGGER.error("Invalid appointment date", e.getClass());
		}
	}

	@Order(10)
	@Test
	void testIsValidAppointmentDateInvalidDate() {
		LOGGER.info(("TEST CASE TC_42"));

		assertThrows(InvalidAppointmentDateException.class, ()->AppointmentServiceImpl.isValidAppointmentDate(LocalDate.of(2000, 5, 1)));

		LOGGER.info("Invalid appointment date test passed");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#isValidAppointmentTime(java.time.LocalTime, java.time.LocalDate)}.
	 */
	@Order(11)
	@Test
	void testIsValidAppointmentTime() {
		LOGGER.info(("TEST CASE TC_43"));

		try
		{
			assertTrue(AppointmentServiceImpl.isValidAppointmentTime(LocalTime.of(15, 0, 0), LocalDate.now()));
			LOGGER.info("Appointment time validation passed");
		}
		catch(InvalidAppointmentTimeException e)
		{
			LOGGER.error("Time validation failed due to ", e.getClass());
		}
	}

	@Order(12)
	@Test
	void testIsValidAppointmentTimeInvalidTime() {
		LOGGER.info(("TEST CASE TC_44"));

		assertThrows(InvalidAppointmentTimeException.class, ()->AppointmentServiceImpl.isValidAppointmentTime(LocalTime.of(1,0,0), LocalDate.now()));
		LOGGER.info("Invalid appointment time test passed");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#isValidLocation(java.lang.String)}.
	 */
	@Order(13)
	@Test
	void testIsValidLocation() {
		LOGGER.info(("TEST CASE TC_45"));

		try
		{
			assertTrue(AppointmentServiceImpl.isValidLocation("Madurai South"));
			LOGGER.info("Appointment location validation passed");
		}
		catch(InvalidAppointmentLocationException e)
		{
			LOGGER.error("Invalid appointment location", e.getClass());
		}

	}

	@Order(14)
	@Test
	void testIsValidLocationInvalidData() {
		LOGGER.info(("TEST CASE TC_46"));
		assertThrows(InvalidAppointmentLocationException.class, ()->AppointmentServiceImpl.isValidLocation("Location 1"));
		LOGGER.info("InvalidAppointmentLocationException validation passed");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#isValidInspectionType(java.lang.String)}.
	 */
	@Order(15)
	@Test
	void testIsValidInspectionType() {
		LOGGER.info(("TEST CASE TC_47"));
		try
		{
			assertTrue(AppointmentServiceImpl.isValidInspectionType("PAY1"));
			LOGGER.info("Appointment Inspection type validation passed");
		}
		catch(InvalidAppointmentInspectionTypeException e)
		{
			LOGGER.error("Appointment Inspection type validation failed", e.getClass());
		}
	}

	@Order(16)
	@Test
	void testIsValidInspectionTypeInvalidData() {
		LOGGER.info(("TEST CASE TC_48"));
		assertThrows(InvalidAppointmentInspectionTypeException.class, ()->AppointmentServiceImpl.isValidInspectionType("Inspection"));
		LOGGER.info("InvalidAppointmentInspectionTypeException is thrown and passed");
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#updateAppointment(int, com.cg.cars.entities.Appointment)}.
	 */
	@Order(17)
	@Test
	void testUpdateAppointment() {
		
		LOGGER.info(("TEST CASE TC_49"));
		AppointmentDTO reference = appointments.get(0);
		reference.setLocation("Tiruchuli");
		try
		{
			AppointmentDTO updated = appointmentService.updateAppointment(reference.getAppointmentId(), AppointmentUtils.convertToAppointment(reference));
			assertEquals(reference, updated);
			System.out.println(appointments.remove(0));
			System.out.println(appointments.add(updated));
			LOGGER.info("Appointment is successfully updated");
		}
		catch(AppointmentExceptions e)
		{
			LOGGER.error("Appointment updation failed due to " + e.getMessage(), e.getClass());
		}
	}

	@Order(18)
	@Test
	void testUpdateAppointmentInvalid() {
		
		LOGGER.info(("TEST CASE TC_50"));
		AppointmentDTO reference = appointments.get(0);
		assertThrows(AppointmentExceptions.class, () -> appointmentService.updateAppointment(reference.getAppointmentId()+100, AppointmentUtils.convertToAppointment(reference)));
		LOGGER.info("Invalid updation failed as expected");	
			
	}

	@Test
	@Order(19)
	void testAddAppointmentWithoutPaymentInvalid() {
		
		LOGGER.info(("TEST CASE TC_51"));
		Address address=new Address("12", "street", "Area", "Chennai", "Tamil Nadu", 123456);
		Customer customer=new Customer(143,"TestName", "testemail2@email.com", "2334567890", LocalDate.of(2000, 1, 1), address);
		Appointment appointment = new Appointment(1, "Chennai", "CHK1", LocalDate.now(), LocalTime.of(13, 0,0), customer, null);
		assertThrows(Exception.class ,()->appointmentService.addAppointment(appointment));
		LOGGER.info("Invalid appointment is not added");		
	}

	/**
	 * Test method for {@link com.cg.cars.service.AppointmentServiceImpl#removeAppointment(int)}.
	 */
	@Order(20)
	@Test
	void testRemoveAppointment() {
		LOGGER.info(("TEST CASE TC_52"));
		try
		{
			AppointmentDTO reference = appointments.get(0);
			assertEquals(reference, appointmentService.removeAppointment(appointments.get(0).getAppointmentId()));
			LOGGER.info("Appointment deletion validation passed");
		}
		catch(AppoitnmentNotFoundException e)
		{
			LOGGER.error("Appointment is not found to be removed", e.getClass());
		}
		
	}

	@Order(21)
	@Test
	void testRemoveAppointmentInvalid() {
		LOGGER.info(("TEST CASE TC_53"));
		assertThrows(AppoitnmentNotFoundException.class, ()->appointmentService.removeAppointment(150));
		LOGGER.info("Appointment not found exception thrown when tried to deletion invalid appointment id as expected");
	}

}
