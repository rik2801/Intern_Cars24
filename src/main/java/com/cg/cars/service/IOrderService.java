package com.cg.cars.service;

import java.util.List;

import com.cg.cars.entities.Order;

import com.cg.cars.exception.OrderServiceException;

import com.cg.cars.model.OrderDTO;

public interface IOrderService {

	public OrderDTO addOrder(Order order) throws OrderServiceException;

	public OrderDTO removeOrder(long id) throws OrderServiceException;

	public OrderDTO updateOrder(long id, Order order) throws OrderServiceException;

	public OrderDTO getOrderDetails(long id) throws OrderServiceException;

	public List<OrderDTO> getAllOrders() throws OrderServiceException;

}
