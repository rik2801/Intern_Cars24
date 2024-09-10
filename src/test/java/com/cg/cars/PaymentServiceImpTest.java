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

import com.cg.cars.entities.Payment;
import com.cg.cars.exception.PaymentServiceException;
import com.cg.cars.model.PaymentDTO;
import com.cg.cars.service.PaymentServiceImp;
import com.cg.cars.utils.PaymentUtils;
/**
 * @author Avinash
 *
 */
@SpringBootTest
class PaymentServiceImpTest {
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());


	@Autowired
	private PaymentServiceImp service;

	List<Payment> list = new ArrayList<>();

	/**
	 * Test method for {@link com.cg.cars.service. PaymentServiceImp#addPayment(com.cg.cars.entities.Payment)}.
	 */
	
	@Test
	void testAddPayment() {
		LocalDate expiryDate = LocalDate.parse("2025-05-24");
		Payment paymentTemp = new Payment(101, "Credit card", "Success", 1, "Avinash", "36971236", expiryDate, 112);
		try {
			service.addPayment(paymentTemp);
		} catch (PaymentServiceException exception) {
			assertEquals("Enter the valid payment detials", exception.getMessage());
		}
		LOGGER.info("Add Payment Caused an Exception");

	}
	
	@Test
	void testAddPayment2() throws PaymentServiceException {
		LocalDate expiryDate = LocalDate.parse("2025-05-24");
		Payment paymentTemp = new Payment(101, "CreditCard", "Success", 1, "avinash", "3258147036971236", expiryDate, 112);

		assertEquals(paymentTemp.getStatus(), service.addPayment(paymentTemp).getStatus());
		LOGGER.info("Add Payment is executed");

	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#removePayment(long)}.
	 */

	@Test
	void testRemovePayment()  {
			try {
				service.removePayment(6);
			} catch (PaymentServiceException exception) {
				assertEquals("Payment does not exist for paymenId to delete", exception.getMessage());
			}
			LOGGER.info("Remove Payment caused an exception");

		}
	
	@Test
	void testRemovePayment1() throws PaymentServiceException  {
		PaymentDTO getPayment=service.getPaymentDetails(96);
		assertEquals(getPayment.getStatus(), service.removePayment(96).getStatus());
		LOGGER.info("Remove Payment Excecuted");

	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#updatePayment(long,com.cg.cars.entities.Payment)}.
	 */
	
	@Test
	void testUpdatePayment() throws PaymentServiceException {
		LocalDate expiryDate = LocalDate.parse("2024-05-24");
		Payment paymentTemp = new Payment(45, "CreditCard", "Failure", 44, "Monisha", "7418523698742334", expiryDate, 365);
		assertEquals("Failure", service.updatePayment(45, paymentTemp).getStatus());
		LOGGER.info("Update Payment Excecuted");
		
	}
	
	@Test
	void testUpdatePayment2() {
		LocalDate expiryDate = LocalDate.parse("2025-05-24");
		Payment paymentTemp = new Payment(11, "Success", "Success", 2, "Monisha", "2121455436632365", expiryDate, 233);
		long paymentId = paymentTemp.getPaymentId();
		try {
		service.updatePayment(paymentId, paymentTemp);
		}
		catch(PaymentServiceException exception)
		{
			assertEquals("Payment does not exist for PaymentId", exception.getMessage());
		}
		LOGGER.info("Update Payment caused an exception");
		
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#getPaymentDetails(long)}.
	 */

	
	@Test
	void testGetPaymentDetails() throws PaymentServiceException{
		PaymentDTO payment = service.getPaymentDetails(100);
		
		assertEquals("Monisha", payment.getCard().getCardName());
		LOGGER.info("Get payment details by id Excecuted");
		
	}

	
	@Test
	void testGetPaymentDetails2()  {
		try {
			service.getPaymentDetails(25);
		} catch (PaymentServiceException exception) {
			assertEquals("Payment does not exist for paymentId", exception.getMessage());
		}
		LOGGER.info("Get payment details caused an exception");
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#getAllPaymentDetails()}.
	 */
	
	@Test
	void testGetAllPaymentDetails() throws PaymentServiceException {
		PaymentDTO payment1 = service.getPaymentDetails(100);
		PaymentDTO payment2 = service.getPaymentDetails(108);
		Payment DTOlist1 = PaymentUtils.convertToPayment(payment1);
		Payment DTOlist2 = PaymentUtils.convertToPayment(payment2);
		list.add(DTOlist1);
		list.add(DTOlist2);
		assertNotNull(list);
		LOGGER.info("Get all payments executed successfully");
		
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#validatePaymentType(com.cg.cars.entities.Payment)}.
	 */
	
	@Test
	void testValidatePaymentType() {
		LocalDate expiryDate = LocalDate.parse("2025-07-01");
		Payment payment1 = new Payment(8, "DebitCard", "Pending", 7, "Avinash", "8520741963214852", expiryDate, 741);
		assertEquals(true, service.validatePaymentType(payment1));
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#validatePaymentStatus(com.cg.cars.entities.Payment)}.
	 */
	@Test
	void testValidatePaymentStatus() {

		LocalDate expiryDate = LocalDate.parse("2025-07-01");
		Payment payment2 = new Payment(8, "DebitCard", "Pending", 7, "Avinash", "8520741963214852", expiryDate, 741);
		assertEquals(true, service.validatePaymentStatus(payment2));

	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#validateCardName(com.cg.cars.entities.Payment)}.
	 */
	@Test
	void testValidateCardName() {
		LocalDate expiryDate = LocalDate.parse("2025-07-01");
		Payment payment2 = new Payment(8, "DebitCard", "Pending", 7, "Avinash", "8520741963214852", expiryDate, 741);

		assertEquals(true, service.validateCardName(payment2));
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#validateCardNumber(com.cg.cars.entities.Payment)}.
	 */
	@Test
	void testValidateCardNumber() {
		LocalDate expiryDate = LocalDate.parse("2025-07-01");
		Payment payment2 = new Payment(8, "DebitCard", "Pending", 7, "Avinash", "8520741963214852", expiryDate, 741);
		assertEquals(true, service.validateCardNumber(payment2));
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#validateCvv(com.cg.cars.entities.Payment)}.
	 */
	@Test
	void testValidateCvv() {
		LocalDate expiryDate = LocalDate.parse("2025-07-01");
		Payment payment2 = new Payment(8, "DebitCard", "Pending", 7, "Avinash", "8520741963214852", expiryDate, 741);
		assertEquals(true, service.validateCvv(payment2));
	}

	/**
	 * Test method for {@link com.cg.cars.service.PaymentServiceImp#validateCardExpiry(java.time.LocalDate)}.
	 */
	@Test
	void testValidateCardExpiry() {
		LocalDate expiryDate = LocalDate.parse("2025-07-01");
		Payment payment2 = new Payment(8, "DebitCard", "Pending", 7, "Avinash", "8520741963214852", expiryDate, 741);
		assertEquals(true, service.validateCardExpiry(payment2));
	}

}
