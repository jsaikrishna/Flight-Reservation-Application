package com.flight.business.service;

import java.util.Date;
import java.util.Scanner;

import com.flight.bean.Address;
import com.flight.bean.Admin;
import com.flight.bean.Customer;
import com.flight.bean.Flight;
import com.flight.bean.Person;
import com.flight.bean.Reservation;
import com.flight.bean.Transportation;
import com.flight.dao.Database;

public class ViewControl {
	Database d=new Database();
	public ViewControl()
	{
		
		
		
	}
	public boolean viewSignUp(Person person)
	{
		//Customer c=(Customer)person;
		Customer c=new Customer(person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender(), person.getDate(), person.getEmail(),person.getPassword());
		return (d.addCustomerToDb(c));
		
	}
	
	public boolean viewLogin(Person person)
	{
		d.signIn(person);
		
		
		return false;
	}
	
	public boolean viewSignOut()
	{
		return false;
	}
	
	public void viewSelectReservation()
	{
		
	}
	
	public boolean viewConfirmReservation()
	{
		return false;
	}
	
	public boolean viewQueryPassenger()
	{
		return false;
	}
	
	public boolean viewDisplayList(Transportation transport)
	{
		return false;
		
	}
	
	public boolean displayQueriedRes(Reservation res)
	{
		return false;
	}
	
	public static void main(String args[])
	{
		ViewControl v=new ViewControl();
		while(true)
		{
			System.out.println("Welcome to Flight Reservation System\n1.Press 1 to Login\n2.Press 2 to Signup\n3.Press 3 to login as admin");
			Scanner s=new Scanner(System.in);
			int input=s.nextInt();
			if(input==1)
			{
				System.out.println("Please enter the credentials:\nUsername and Password:");
				Person p=new Person();
				p.setEmail(s.next());
				p.setPassword(s.next());
				v.viewLogin(p);
				
				
				//call signin
			}
			else if(input==2)
			{
				System.out.println("Please enter your details:\nPlease enter first name and last name");
				Person p=new Person();
				p.setFirstName(s.next());
				p.setLastName(s.next());
				System.out.println("Please enter email id,gender and password:");
				p.setEmail(s.next());
				p.setDate(new Date());
				p.setGender(s.next());
				p.setPassword(s.next());
				System.out.println("Please address.country,state,city,street,unit,zipcode");
				Address a=new Address();
				a.setCountry(s.next());
				a.setState(s.next());
				a.setCity(s.next());
				a.setStreet(s.next());
				a.setUnit(s.nextInt());
				a.setZipCode(s.nextInt());
				p.setAddress(a);
				Customer c=new Customer(p.getFirstName(), p.getLastName(), p.getAddress(), p.getGender(), p.getDate(), p.getEmail(), p.getPassword());
				boolean status=c.signUp(p);
				if(status)
				{
					System.out.println("Successfully signed up");
				}
				else
				{
					System.out.println("Sign up failed, Please signup again with correct details");
				}
				
				
			}
			else if(input==3)
			{
				System.out.println("Please enter the credentials:\nUsername and Password:");
				Admin a=new Admin();
				a.setEmail(s.next());
				a.setPassword(s.next());
				if(v.viewLogin((Person)a))
				{
					while(true)
					{
						System.out.println("Please make a choice:\n1)Make a reservation\n2)Cancel a reservation\n3)Logout");
						int choice=s.nextInt();
						if(choice==1)
						{
							
						}
						else if(choice==2)
						{
							
						}
						else if(choice==3)
						{
							break;
						}
						else
						{
							System.out.println("Please make a right choice");
						}
					}
				}
			}
			else
			{
				System.out.println("You have made wrong choice. Please make a valid choice between 1 and 2\n");
				
			}
		}
	}
	
	

}
