package com.cg.cars.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.cars.dao.ICustomerRepository;
import com.cg.cars.dao.IOrderRepository;
import com.cg.cars.entities.Customer;
import com.cg.cars.entities.Order;
import com.cg.cars.exception.OrderServiceException;
import com.cg.cars.model.OrderDTO;
import com.cg.cars.utils.OrderUtils;

/**
*Author     : Dhivya
*Date       :08-04-2021
*Description:This is  Order Service Class that 
*			 provide services to add a order, remove order, update an order 
*            and to view order details
**/

@Service
public class OrderServiceImp implements IOrderService {

	@Autowired
	private IOrderRepository orderRepo;
	
	@Autowired
	private ICustomerRepository customerRepo;

	/**
	*Description	:To add Order to the database
	*Input Params	:Order object to be added to the database
	*Return Value	:OrderDTO object
	*Exception	    :OrderServiceException-It is raised when order already exist   
	**/
	
	@Transactional
	@Override
	public OrderDTO addOrder(Order order)throws OrderServiceException 
	{
		isValidOrder(order);
		Optional<Order> addOrdertemp = orderRepo.findById(order.getOrderId());
		if (addOrdertemp.isEmpty()){
			Order addOrder = orderRepo.save(order);
			return OrderUtils.convertToOrderDto(addOrder);
		}
		else
		{
			throw new OrderServiceException("Order already exists");
		}
	}

	
	/**
	*Description	:To delete Order from the database
	*Input Params	:Order id to be deleted from the database
	*Return Value	:OrderDTO object of the Order been deleted
	*Exception	    :OrderServiceException-It is raised when order ID doesn't exist   
	**/

	@Transactional
	@Override
	public OrderDTO removeOrder(long id) throws OrderServiceException {
		Optional<Order> order = orderRepo.findById(id);
		if (order.isPresent()) {
			orderRepo.delete(order.get());
			return OrderUtils.convertToOrderDto(order.get());
		} 
		else {
			throw new OrderServiceException("orderId does not exist to delete");
		}
	}


	
	/**

	*Description	:To update Order details to the database
	*Input Params	:Order to be updated in the database
	*Return Value	:OrderDTO object of the Order been updated
	*Exception	    :OrderServiceException-It is raised when order doesn't exist   
	**/

	@Transactional
	@Override
	public OrderDTO updateOrder(long id, Order order) throws OrderServiceException {
		if (order == null)
			throw new OrderServiceException("Order is null");
		isValidOrder(order);
		Order existingOrder= orderRepo.findById(order.getOrderId()).orElse(null);
		if (existingOrder == null) {
			throw new OrderServiceException("OrderId not found");
		}
        else {
			return OrderUtils.convertToOrderDto(orderRepo.save(order));
		}
	}


	
	/**
	*Description	:To fetch Order details from the database
	*Input Params	:Order ID object to be fetched from the database
	*Return Value	:OrderDTO object of the Order been fetched
	*Exception	    :OrderServiceException-It is raised when order id doesn't exist   
	**/

	@Transactional
	@Override
	public OrderDTO getOrderDetails(long id)throws OrderServiceException {
		Optional<Order> orderTemp = orderRepo.findById(id);
		if (orderTemp.isEmpty()) {
			throw new OrderServiceException("OrderId does not exist");
		} 
		else {
			Order getOrder = orderRepo.findById(id).orElse(null);
			return OrderUtils.convertToOrderDto(getOrder);
		}
	}
	
	/**
	*Description	:To fetch Order details from the database
	*Return Value	:List<OrderDTO> object of the Order been fetched
	*Exception	:OrderServiceException-It is raised when order not found  
	**/

	@Transactional
	@Override
	public List<OrderDTO> getAllOrders() throws OrderServiceException {
		List<Order> orderTemp = orderRepo.findAll();
		if (orderTemp.isEmpty()) {
			throw new OrderServiceException("Order not found");
		} 
		else {
			List<Order> getAllOrders = orderRepo.findAll();
			return OrderUtils.convertToOrderDtoList(getAllOrders);
		}
	}

	public boolean isValidOrder(Order order) throws OrderServiceException
	{
		if( isValidBillingDate(order.getBillingDate()) 
				&& isValidAmount(order.getAmount()) && isValidCustomer(order.getCustomer()))
			return true;
		throw new OrderServiceException("This is not a valid order. Order failed");
	}
	
		
	public boolean isValidBillingDate(LocalDate date) throws OrderServiceException
	{
		//Return true only if billing date is today's date
		if(date.compareTo(LocalDate.now())==0)
			return true;
		throw new OrderServiceException("Billing date is not valid");
	}
	
	public boolean isValidAmount(double amount) throws OrderServiceException
	{
		if((long)Math.floor(amount)>0)
			return true;
		throw new OrderServiceException("Amount is not valid");
	}
	
	public boolean isValidCustomer(Customer customer) throws OrderServiceException
	{
		Optional<Customer> databaseCustomer=customerRepo.findById(customer.getUserId());
		if(databaseCustomer.isPresent())
			return true;
		throw new OrderServiceException("Customer is not available");
	}
	

}
