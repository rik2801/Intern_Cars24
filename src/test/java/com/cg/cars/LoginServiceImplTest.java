package com.cg.cars;

import static org.junit.jupiter.api.Assertions.*;

import com.cg.cars.entities.User;
import com.cg.cars.exception.LoginNotFoundException;
import com.cg.cars.exception.UserNotFoundException;
import com.cg.cars.service.ILoginService;
import com.cg.cars.service.IUserService;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author Sarath Sathyan
 *
 */
@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest
class LoginServiceImplTest {

	@Autowired
	private ILoginService service;

	@Autowired
	private IUserService userService;
	final Logger LOGGER =	LoggerFactory.getLogger(this.getClass());

	private static  long id;

	/*
	* Test method for {@link com.cg.cars.service.LoginServiceImpl #Login (com.cg.cars.entities.User)}.
	 */
	@Order(1)
	@Test
	void testLogin() {

		LOGGER.info("TEST CASE TC_01");
		User user=new User(123, "password", "role", "username");
		id = userService.addUser(user).getUserId();
		user.setUserId(id);
		try {
			assertTrue(service.Login(user));
			LOGGER.info("User is succesfully logged in");
		} catch (LoginNotFoundException | UserNotFoundException e) {
			fail("User is not valid");
		}
	}


	@Order(2)
	@Test
	void testLoginFail1() {

		LOGGER.info("TEST CASE TC_02");
		assertThrows(LoginNotFoundException.class, ()->service.Login(null));
		LOGGER.info("Login not found exception is thrown");
	}

	@Order(3)
	@Test
	void testLoginFail2() {

		LOGGER.info("TEST CASE TC_03");
		User user=new User(123, "newpwd", "role", "username");
		user.setUserId(id);
		assertThrows(UserNotFoundException.class, ()->service.Login(user));
		LOGGER.info("User not found exception is thrown for invalid password");
	}

}
