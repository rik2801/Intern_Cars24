package com.cg.cars.service;

import java.util.List;

import com.cg.cars.entities.Payment;
import com.cg.cars.exception.PaymentServiceException;
import com.cg.cars.model.PaymentDTO;

public interface IPaymentService {
	public PaymentDTO addPayment(Payment payment)throws PaymentServiceException;

	public PaymentDTO removePayment(long paymentId) throws PaymentServiceException;

	public PaymentDTO updatePayment(long paymentId, Payment payment) throws PaymentServiceException;

	public PaymentDTO getPaymentDetails(long paymentId)throws PaymentServiceException;

	public List<PaymentDTO> getAllPaymentDetails() throws PaymentServiceException;
}
