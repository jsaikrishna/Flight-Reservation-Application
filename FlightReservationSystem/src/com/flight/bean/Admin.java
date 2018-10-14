package com.flight.bean;

import java.util.Date;

public class Admin extends Person {
	
	public Admin()
	{
		
	}
	
	public Admin(String firstName, String lastName, Address address, String gender, Date date, String email,
			String password) {
		super(firstName, lastName, address, gender, date, email, password);
		// TODO Auto-generated constructor stub
	}

	public void makeReservation(Passenger passenger)
	{
		
	}
	
	public void queryDisplayCancel()
	{
		
	}
	public boolean login()
	{
		return false;
	}
	public boolean signOut()
	{
		return false;
	}

}
