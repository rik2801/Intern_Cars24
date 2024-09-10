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

import com.cg.cars.entities.Order;
import com.cg.cars.exception.OrderServiceException;
import com.cg.cars.model.OrderDTO;
import com.cg.cars.service.IOrderService;

/**
*Author: Dhivya
*Date:08-04-2021
*Description:This is Order Controller Layer
**/

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	private IOrderService orderService;

	/**
	*Description	:To add Order to the database
	*Input Params	:Order object to be added to the database
	*Return Value	:OrderDTO object 
	**/
	
	@PostMapping(path = "/addOrder", consumes = "application/json")
	public ResponseEntity<Object> addOrder(@RequestBody Order order) {
		OrderDTO resultorder;
		try {
			resultorder = orderService.addOrder(order);
			return new ResponseEntity<Object>(resultorder, HttpStatus.OK);
		} catch (OrderServiceException e) {
			return new ResponseEntity<Object>(e.toString(), HttpStatus.BAD_REQUEST);       
		}
	}

	/**
	*Description	:To delete Order from the database
	*Input Params	:Order id to be deleted from the database
	*Return Value	:OrderDTO object of the Order been deleted
	**/
	
	@DeleteMapping(path = "/deleteOrder/{orderId}", produces = "application/json")
	public ResponseEntity<Object> removeOrder(@PathVariable long orderId) {
		try {
			return new ResponseEntity<Object>(orderService.removeOrder(orderId),HttpStatus.OK);
		} catch (OrderServiceException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);       
		}
	}

	/**

	*Description	:To update Order details to the database
	*Input Params	:Order to be updated in the database
	*Return Value	:OrderDTO object of the Order been updated
	**/
	
	@PutMapping("/updateOrder/{id}")
	public ResponseEntity<Object> updateOrder(@RequestBody  Order order) {
		OrderDTO resultOrder;
		try {
			resultOrder = orderService.updateOrder(order.getOrderId(),order);
			return new ResponseEntity<Object>(resultOrder, HttpStatus.OK);
		} catch (OrderServiceException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}


	/**
	*Description	:To fetch Order details from the database
	*Input Params	:Order ID object to be fetched from the database
	*Return Value	:OrderDTO object of the Order been fetched
	**/
	
	@GetMapping(path = "/getOrderDetails/{orderId}", produces = "application/json")
	public ResponseEntity<Object> getOrderDetails(@PathVariable long orderId) {
		OrderDTO resultOrder;
		try {
			resultOrder = orderService.getOrderDetails(orderId);
			return new ResponseEntity<Object>(resultOrder, HttpStatus.OK);
		} catch (OrderServiceException e) {
			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	*Description	:To fetch Order details from the database
	*Return Value	:List<OrderDTO> object of the Order been fetched
	*Exception	:OrderServiceException-It is raised when order not found  
	**/

	@GetMapping(path = "/getAllOrders", produces = "application/json")
	public ResponseEntity<List<OrderDTO> > getAllOrder() throws OrderServiceException {
		List<OrderDTO> resultOrder = orderService.getAllOrders();
		return new ResponseEntity<List<OrderDTO> >(resultOrder, HttpStatus.OK);
	}



}
