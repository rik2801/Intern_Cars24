package com.cg.cars.exception;

@SuppressWarnings("serial")
public class OrderServiceException extends RuntimeException {

	public OrderServiceException(String msg) {
		super(msg);
	}

}
