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

import com.cg.cars.entities.Address;
import com.cg.cars.entities.Customer;
import com.cg.cars.entities.Order;
import com.cg.cars.entities.Payment;
import com.cg.cars.exception.OrderServiceException;
import com.cg.cars.model.OrderDTO;
import com.cg.cars.service.OrderServiceImp;
import com.cg.cars.utils.OrderUtils;

/**
 * @author Dhivya
 *
 */
@SpringBootTest
class OrderServiceImpTest {
	
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	OrderServiceImp service;

	/**
	 * Test method for {@link com.cg.cars.service.OrderServiceImp #addOrder (com.cg.cars.entities.Order)}.
	 */
	@Test
	void testAddOrder() {
		LocalDate expiryDate = LocalDate.parse("2025-05-25");
		LocalDate DobDate = LocalDate.parse("1997-08-12");
		LocalDate BillingDate = LocalDate.parse("2021-04-10");
		Address address = new Address("63", "7th street", "Porur", "Chennai", "Tamil Nadu", 600021);
		Customer customer = new Customer(3,"DhivyaShree", "divyashree@gmail.com", "9040380958", DobDate, address);
		Payment payment = new Payment(25, "DebitCard", "Success", 26, "DhivyaS", "6321987425898521", expiryDate, 258);
		Order orderTemp = new Order(12, 50000, BillingDate, customer, payment);
		try {
			service.addOrder(orderTemp);
		} catch (OrderServiceException exception) {
			assertEquals("Billing date is not valid", exception.getMessage());
		}
		LOGGER.info("Add Order caused exception");
	}
	
	@Test
	void testAddOrder2() {
		LocalDate expiryDate = LocalDate.parse("2025-05-25");
		LocalDate DobDate = LocalDate.parse("1997-08-12");
		LocalDate BillingDate = LocalDate.parse("2021-04-10");
		Address address = new Address("63", "7th street", "Porur", "Chennai", "Tamil Nadu", 600021);
		Customer customer = new Customer(3,"DhivyaShree", "divyashree@gmail.com", "9040380958", DobDate, address);
		Payment payment = new Payment(25, "DebitCard", "Success", 26, "DhivyaS", "6321987425898521", expiryDate, 258);
		Order orderTemp = new Order(12, 50000, BillingDate, customer, payment);
		assertEquals(orderTemp.getAmount(), service.addOrder(orderTemp).getAmount());
		LOGGER.info("Add Order executed");
	}
	
	/**
	 * Test method for {@link com.cg.cars.service.OrderServiceImp #removeOrder(long)}.
	 */
	@Test
	void testRemoveOrder() {
		try {
			service.removeOrder(22);
		} catch (OrderServiceException exception) {
			assertEquals("orderId does not exist to delete", exception.getMessage());
		}
		LOGGER.info("Remove Order caused exception");

	}
	
	@Test
	void testRemoveOrder1() {
		assertEquals(service.getOrderDetails(122).getBillingDate(), service.removeOrder(122).getBillingDate());
		LOGGER.info("Remove Order exceuted");
	}

	/**
	 * Test method for {@link com.cg.cars.service.OrderServiceImp #updateOrder(long,com.cg.cars.entities.Order)}.
	 */
	@Test
	void testUpdateOrder() {
		LocalDate expiryDate = LocalDate.parse("2025-04-10");
		LocalDate DobDate = LocalDate.parse("1997-08-12");
		LocalDate BillingDate = LocalDate.parse("2021-04-10");
		Address address = new Address("63", "7th street", "Porur", "Chennai", "Tamil Nadu", 600021);
		Customer customer = new Customer(3,"DhivyaShree", "divyashree@gmail.com", "9040380958", DobDate, address);
		Payment payment = new Payment(25, "DebitCard", "Success", 26, "DhivyaS", "6321987425898521", expiryDate, 258);
		Order orderTemp = new Order(122,32000,BillingDate, customer, payment);
		Order orderUpd=OrderUtils.convertToOrder(service.updateOrder(122, orderTemp));
		assertEquals(32000, orderUpd.getAmount());
		LOGGER.info("Update Order executed");
	}

	@Test
	void testUpdateOrder2() {
		LocalDate expiryDate = LocalDate.parse("2025-04-10");
		LocalDate DobDate = LocalDate.parse("1997-08-12");
		LocalDate BillingDate = LocalDate.parse("2021-04-10");
		Address address = new Address("63", "7th street", "Porur", "Chennai", "Tamil Nadu", 600021);
		Customer customer = new Customer(3,"DhivyaShree", "divyashree@gmail.com", "9040380958", DobDate, address);
		Payment payment = new Payment(25, "DebitCard", "Success", 26, "DhivyaS", "6321987425898521", expiryDate, 258);
		Order orderTemp = new Order(122,32000,BillingDate, customer, payment);
		try {
			service.updateOrder(79, orderTemp);
		} catch (OrderServiceException exception) {
			assertEquals("OrderId not found", exception.getMessage());
		}
		LOGGER.info("Update Order caused exception");

	}
	
	/**
	 * Test method for {@link com.cg.cars.service.OrderServiceImp #getOrderDetails (long)}.
	 */
	@Test
	void testGetOrderDetails() {
		OrderDTO order = service.getOrderDetails(102);
		assertEquals(52500, order.getAmount());
		LOGGER.info("Get Order details executed");

	}

	
	@Test
	void testGetOrderDetails2() {
		try {
			service.getOrderDetails(79);
		} catch (OrderServiceException exception) {
			assertEquals("OrderId does not exist", exception.getMessage());
		}
		LOGGER.info("Get Order Details caused exception");
	}

	/**
	 * Test method for {@link com.cg.cars.service.OrderServiceImp #getAllOrders ()}.
	 */
	@Test
	void testGetAllOrderDetails() {
		List<Order> list = new ArrayList<>();
		OrderDTO order1 = service.getOrderDetails(123);
        OrderDTO order2 = service.getOrderDetails(102);
		Order order3 = OrderUtils.convertToOrder(order1);
        Order order4 = OrderUtils.convertToOrder(order2);
		list.add(order3);
		list.add(order4);
		assertNotNull(list);
		LOGGER.info("Get All Order details executed");
	}


}
