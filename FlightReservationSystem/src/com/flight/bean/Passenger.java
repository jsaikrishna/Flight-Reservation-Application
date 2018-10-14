package com.flight.bean;

import java.util.Date;

public class Passenger extends Person {
	private String passportNo;
	private String visaType;
	private String ticketType;
	private String mealType;
	
	public Passenger()
	{
		
	}
	
	public Passenger(String firstName, String lastName, Address address, String gender, Date date, String email,
			String password,String passportNo, String visaType, String ticketType, String mealType) {
		super(firstName, lastName, address, gender, date, email, password);
		this.passportNo = passportNo;
		this.visaType = visaType;
		this.ticketType = ticketType;
		this.mealType = mealType;
	}
	
	public void displayDetails()
	{
		
	}

	public String getPassportNo() {
		return passportNo;
	}

	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}

	public String getVisaType() {
		return visaType;
	}

	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}

	public String getTicketType() {
		return ticketType;
	}

	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}

	public String getMealType() {
		return mealType;
	}

	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	
	
	
	

}
