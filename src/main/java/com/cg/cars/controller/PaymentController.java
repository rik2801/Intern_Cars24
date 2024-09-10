package com.cg.cars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.cars.entities.Payment;
import com.cg.cars.exception.PaymentServiceException;
import com.cg.cars.model.PaymentDTO;
import com.cg.cars.service.IPaymentService;

/**
 * Author: Avinash 
 * Date:08-04-2021 
 * Description:This is Payment Controller Layer
 **/

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

	@Autowired
	private IPaymentService paymentService;

	/**
	 * Description  :To add Payment to the database 
	 * Input Params :Payment object to be added to the database 
	 * Return Value :PaymentDTO object 
	 * Exception    : PaymentServiceException-It is raised when payment already exist
	 * @throws PaymentServiceException 
	 **/

	@PostMapping("/addPayment")
	@ExceptionHandler()
	public ResponseEntity<PaymentDTO> addPayment(@RequestBody Payment payment) throws PaymentServiceException {
		PaymentDTO addPayment = paymentService.addPayment(payment);
		return new ResponseEntity<PaymentDTO>(addPayment, HttpStatus.OK);
	}

	/**
	 * Description :To delete Payment from the database 
	 * Input Params :Payment id to be deleted from the database 
	 * Return Value :PaymentDTO object of the payment been deleted 
	 * Exception :PaymentServiceException-It is raised when payment ID doesn't exist
	 * @throws PaymentServiceException 
	 **/

	@DeleteMapping("/removePayment/{paymentId}")
	public ResponseEntity<PaymentDTO> removePayment(@PathVariable long paymentId) throws PaymentServiceException {
		PaymentDTO removePayment = paymentService.removePayment(paymentId);
		return new ResponseEntity<PaymentDTO>(removePayment, HttpStatus.OK);
	}

	/**
	 * 
	 * Description :To update Payment details to the database 
	 * Input Params :Payment to be updated in the database 
	 * Return Value :PaymentDTO object of the payment been updated 
	 * Exception :PaymentServiceException-It is raised when payment doesn't exist
	 * @throws PaymentServiceException 
	 **/

	@PutMapping("/updatePayment/{paymentId}")
	public ResponseEntity<PaymentDTO> updatePayment(@RequestBody Payment payment) throws PaymentServiceException {
		PaymentDTO updatePayment = paymentService.updatePayment(payment.getPaymentId(), payment);
		return new ResponseEntity<PaymentDTO>(updatePayment, HttpStatus.OK);
	}

	/**
	 * Description :To fetch Payment details from the database 
	 * Input Params :Payment ID object to be fetched from the database 
	 * Return Value :PaymentDTO object of the payment been fetched 
	 * Exception :PaymentServiceException-It is raised when payment Id doesn't exist
	 **/

	@GetMapping("/getPayment/{paymentId}")
	public ResponseEntity<PaymentDTO> getPaymentDetails(@PathVariable long paymentId) throws PaymentServiceException {
		PaymentDTO getByPaymentId = paymentService.getPaymentDetails(paymentId);
		return new ResponseEntity<PaymentDTO>(getByPaymentId, HttpStatus.OK);
	}

	/**
	 * Description :To fetch payment details from the database 
	 * Return Value:List<PaymentDTO> object of the payment been fetched 
	 * Exception : PaymentServiceException-It is raised when payment not found
	 * @throws PaymentServiceException 
	 **/

	@GetMapping("/getAllPayment")
	public ResponseEntity<List<PaymentDTO> > getAllPayment() throws PaymentServiceException {
		List<PaymentDTO> getAllPayment = paymentService.getAllPaymentDetails();
		return new ResponseEntity<List<PaymentDTO> >(getAllPayment, HttpStatus.OK);
	}

}
