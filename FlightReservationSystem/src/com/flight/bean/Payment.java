package com.flight.bean;

import java.util.Date;

import com.flight.business.service.ViewControl2;

public class Payment {
	
	private String cardType;
	private Date expirtDate;
	private int cvv;
	private String nameOnCard;
	private Address billingAddress;
	
	public Payment()
	{
		
	}
	
	public Payment(String cardType, Date expirtDate, int cvv, String nameOnCard, Address billingAddress) {
		super();
		this.cardType = cardType;
		this.expirtDate = expirtDate;
		this.cvv = cvv;
		this.nameOnCard = nameOnCard;
		this.billingAddress = billingAddress;
	}
	
	public void makePayment(Reservation reservation)
	{
		ViewControl2 vc=new ViewControl2();
		
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public Date getExpirtDate() {
		return expirtDate;
	}

	public void setExpirtDate(Date expirtDate) {
		this.expirtDate = expirtDate;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getNameOnCard() {
		return nameOnCard;
	}

	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	
	
	

}
