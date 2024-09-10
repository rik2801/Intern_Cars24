package com.cg.cars.model;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cg.cars.entities.Card;

@Component
public class PaymentDTO {

	private long paymentId;

	private String type;

	private String status;

	private Card card;

	public PaymentDTO() {
		super();
	}

	public PaymentDTO(long paymentId, String type, String status, long id, String cardName, String cardNumber,
			LocalDate cardExpiry, int cvv) {
		super();
		this.paymentId = paymentId;
		this.type = type;
		this.status = status;
		this.card = new Card(id, cardName, cardNumber, cardExpiry, cvv);
	}

	public long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(long paymentId) {
		this.paymentId = paymentId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", type=" + type + ", status=" + status + ", card=" + card + "]";
	}

}
