package com.cg.cars.utils;

import java.util.ArrayList;
import java.util.List;

import com.cg.cars.entities.Customer;
import com.cg.cars.entities.Order;
import com.cg.cars.model.OrderDTO;

public class OrderUtils {

	public static List<OrderDTO> convertToOrderDtoList(List<Order> list) {
		List<OrderDTO> orderDTOList = new ArrayList<OrderDTO>();
		for (Order ord : list)
			orderDTOList.add(convertToOrderDto(ord));
		return orderDTOList;
	}

	public static Order convertToOrder(OrderDTO orderDTO) {
		Order order = new Order();

		order.setOrderId(orderDTO.getOrderId());
		order.setAmount(orderDTO.getAmount());
		order.setBillingDate(orderDTO.getBillingDate());
		order.setCustomer((Customer) orderDTO.getCustomer());
		order.setPaymentMethod(orderDTO.getPaymentMethod());
		return order;
	}

	public static OrderDTO convertToOrderDto(Order order) {
		OrderDTO orderDTO = new OrderDTO();

		orderDTO.setOrderId(order.getOrderId());
		orderDTO.setAmount(order.getAmount());
		orderDTO.setBillingDate(order.getBillingDate());
		orderDTO.setCustomer(order.getCustomer());
		orderDTO.setPaymentMethod(order.getPaymentMethod());

		return orderDTO;
	}
}
