package com.flight.business.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.flight.bean.Address;
import com.flight.bean.Admin;
import com.flight.bean.AvailableTransport;
import com.flight.bean.Customer;
import com.flight.bean.Flight;
import com.flight.bean.Passenger;
import com.flight.bean.Payment;
import com.flight.bean.Person;
import com.flight.bean.Reservation;
import com.flight.bean.SelectedTransport;
import com.flight.bean.Transportation;
import com.flight.dao.Database;

public class ViewControl {
	Database d=new Database();
	public ViewControl(){}
	public boolean viewSignUp(Person person)
	{
		//Customer c=(Customer)person;
		Customer c=new Customer(person.getFirstName(), person.getLastName(), person.getAddress(), person.getGender(), person.getDate(), person.getEmail(),person.getPassword());
		return (d.addCustomerToDb(c));
		
	}
	
	public Customer viewLogin(Person person)
	{
		return d.signIn(person);
		//return false;
	}
	
	public boolean viewSignOut()
	{
		return false;
	}
	
	public void viewSelectReservation()
	{
		
	}
	
	public int viewConfirmReservation(Reservation r)
	{
		return d.addReservationtToDb(r);
	}
	
	public List<Passenger> viewQueryPassenger()
	{
		return d.DisplayPassengers();
	}
	
	public AvailableTransport viewDisplayList(Transportation transport)
	{
		return d.getFlightListFromDb(transport);
		
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
			System.out.println("Welcome to Flight Reservation System\n1.Press 1 to Login\n2.Press 2 to Signup\n3.Press 3 to login as admin\n4.Press 4 to exit");
			Scanner s=new Scanner(System.in);
			int input=s.nextInt();
			if(input==1)
			{
				System.out.println("Please enter the credentials:\nUsername and Password:");
				Person p=new Person();
				p.setEmail(s.next());
				p.setPassword(s.next());
				//v.viewLogin(p);
				Customer c=new Customer();
				Customer customer=c.login(p);
				//call signin
//				if(p.getEmail().matches(".*@.*\\..*"))
//				{
//					System.out.println("Hi");
//				}
				if(!p.getEmail().matches(".*@.*\\..*") || customer==null || !customer.getPassword().equals(p.getPassword()))
				{
					System.out.println("Please enter correct credentials");
					continue;
				}
				System.out.println("Logged in successfully");
				
				Transportation t=new Transportation();
				while(true)
				{
					System.out.println("1)Book flight\n2)Cancel Flight\n3)Modify Class\n4)Checkin\n5)Reserve seats\n6)Exit");
					int k=s.nextInt();
					if(k==1)
					{
					
					
						System.out.println("Please let us know whether the trip is one way(1) or round trip(2), Press 1 or 2");
						int selection=s.nextInt();
						System.out.println("Please enter the departure date in yyyy-mm-dd :");
						Calendar dep=Calendar.getInstance();
						String[] str=s.next().split("-");
						dep.set(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
						t.setDepartureDate(dep);
						if(selection==2)
						{
							System.out.println("Please enter the arrival date in yyyy-mm-dd :");
							Calendar arr=Calendar.getInstance();
							String[] str2=s.next().split("-");
							arr.set(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]), Integer.parseInt(str2[2]));
							t.setArrivalDate(arr);
						}
						System.out.println("Please enter no of passengers:");
						int noOfPass=s.nextInt();
						System.out.println("Please enter passenger details:");
						List<Passenger> pass=new ArrayList<>();
						for(int i=0;i<noOfPass;i++)
						{
							System.out.println("For passenger:"+(i+1));
							System.out.println("Please enter first name,last name, email, gender");
							String fname=s.next();
							String lname=s.next();
							String email=s.next();
							String gender=s.next();
							System.out.println("Please enter address:country,state,city,street,unit,zipcode");
							Address a=new Address();
							a.setCountry(s.next());
							a.setState(s.next());
							a.setCity(s.next());
							a.setStreet(s.next());
							a.setUnit(s.nextInt());
							a.setZipCode(s.nextInt());
							p.setAddress(a);
							System.out.println("Please enter passport number,visatype,tickettype and meal preference");
							String passportNo=s.next();
							String visaType=s.next();
							String ticketType=s.next();
							String mealType=s.next();
							Passenger passenger=new Passenger(fname, lname, a, gender, null, email, null, passportNo, visaType, ticketType, mealType);
							pass.add(passenger);
						}
						System.out.println(t.getArrivalDate());
						System.out.println("Please enter source and destination");
						t.setSourceAirport(s.next());
						t.setDestinationAirpoty(s.next());
						AvailableTransport at=v.viewDisplayList(t);
						if(at!=null)
						{
							System.out.println("The flights suitable for you are:");
							
							for(int i=0;i<at.getAvailList().size();i++)
							{
								System.out.println(i+1+")"+at.getAvailList().get(i).getAircraft());
							}
							System.out.println("Select your choice:");
							int choice=s.nextInt()-1;
							while(true)
							{
							System.out.println("Please enter Credit/Debit card details for payment: CardType,CardNo,NameOnCard,CVV,ExpiryDate(yyyy-mm-dd)");
							Payment pay=new Payment();
							
							System.out.println("Please Enter the Card type:");
							pay.setCardType(s.next());
							while(true)
							{
							    try {
							    //	s=new Scanner(System.in);
									s.nextInt();
									break;
								}
								catch(Exception e)
								{
									System.out.println("Please Enter A Valid Card Number!! \n");
									s.next();
									continue;
								}
							}
							System.out.println("Please Enter the Name on the Card:");
							pay.setNameOnCard(s.next());
							System.out.println("Please Enter the CVV:");
							pay.setCvv(s.nextInt());
							System.out.println("Please Enter the Expiration date:");
							Calendar arr=Calendar.getInstance();
							String[] str2=s.next().split("-");
							arr.set(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]), Integer.parseInt(str2[2]));
							Calendar c_new = Calendar.getInstance();
							if (arr.getTimeInMillis() - c_new.getTimeInMillis() < 0)
							{
								System.out.println("Card Has Expired! Please Enter Another Card's Details:");
								continue;
							}
							pay.setExpirtDate(arr.getTime());
							
							pay.setBillingAddress(c.getAddress());
							
							Transportation transportation=at.getAvailList().get(choice);
							
							//Seats booking
//							String seats=transportation.getSeatsBooked();
//							if(seats.equals(""))
//							{
//								System.out.println("Please select a seat between 1 to 10");
//							}
							
							//
							SelectedTransport st=new SelectedTransport();
							st.addSelectedTransport(transportation);
							Reservation r=new Reservation();
							r.setTransport(st);
							r.setCustomer(customer);
							int id=v.viewConfirmReservation(r);
//							if(selection==2)
//							{
//								System.out.println("The return flights avaiilable are:");
//								String dest=t.getDestinationAirpoty();
//								t.setDestinationAirpoty(t.getSourceAirport());
//								t.setSourceAirport(dest);
//								AvailableTransport at2=v.viewDisplayList(t);
//								for(int i=0;i<at2.getAvailList().size();i++)
//								{
//									System.out.println(i+1+")"+at2.getAvailList().get(i).getAircraft());
//								}
//								System.out.println("Select your choice:");
//								int choice2=s.nextInt();
//								Transportation transportation2=at.getAvailList().get(choice2);
//								SelectedTransport st2=new SelectedTransport();
//								st2.addSelectedTransport(transportation2);
//								Reservation r2=new Reservation();
//								r2.setTransport(st2);
//								r2.setCustomer(customer);
//								v.viewConfirmReservation(r2);
//							}
							
							
							System.out.println("Successfully booked the flight with reservation ID:"+id);
							break;
						 }
						
						}
						else 
						{
							System.out.println("Flights for this selection do not exist in the DB!!");
						}
						
						
					}
					else if(k==2)
					{
						System.out.println("Please enter your Reservation ID");
						int id=s.nextInt();
						if(v.d.deleteReservationtInDb(id))
						{
							System.out.println("Reservation has been cancelled");
						}
						else
						{
							System.out.println("Please enter a valid reservationn Id to cancel the reservation");
						}
						
					}
					else if(k==3)
					{
						System.out.println("Please enter your Reservation ID");
						int id=s.nextInt();
						Reservation reservation=v.d.getReservationDetails(id);
						if(reservation.getTransport().getSelectedList().get(0).getClass().equals("Economy"))
						{
							System.out.println("Please press 1 to change class to Business from Economy");
						}
						else
							System.out.println("Please press 1 to change class to Economy from Business");
						if(s.nextInt()==1)
						{
							System.out.println("Changes have been made accordingly");
						}
						else
						{
							System.out.println("You have given wrong input.No changes has been made");
						}
					}
					else if(k==4)
					{
						System.out.println("Please enter your Reservation ID");
						int id=s.nextInt();
						Reservation reservation=v.d.getReservationDetails(id);
						Calendar cal=reservation.getTransport().getSelectedList().get(0).getDepartureDate();
						Calendar current=Calendar.getInstance();
						if(cal.getTimeInMillis()-current.getTimeInMillis()<=86400000)
						{
							System.out.println("Checked in and boarding pass has been mailed");
						}
						else
						{
							System.out.println("Checkin can only be done before 24hrs from the time of departure");
						}
					}
					else if(k==6)
					{
						break;
					}
					else if(k==5)
					{
						System.out.println("Please enter your Reservation ID");
						int id=s.nextInt();
						Reservation reservation=v.d.getReservationDetails(id);
						Transportation trans=reservation.getTransport().getSelectedList().get(0);
						System.out.println(trans.getAircraft());
						v.d.returnAvailableSeats(trans);
						
					}
					else
					{
						System.out.println("Please make a valid choice");
					}
				}
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
				if(!a.getEmail().equals("aln.santosh@gmail.com"))
				{
					System.out.println("Please enter valid credentials");
					continue;
				}
				if(v.viewLogin((Person)a)!=null)
				{
					Transportation t=new Transportation();
					while(true)
					{
						System.out.println("Please make a choice:\n1)Make a reservation\n2)Cancel a reservation\n3)Display Passengers\n4)Logout");
						int choice=s.nextInt();
						if(choice==1)
						{
							System.out.println("Please enter the departure date in yyyy-mm-dd :");
							Calendar dep=Calendar.getInstance();
							String[] str=s.next().split("-");
							dep.set(Integer.parseInt(str[0]), Integer.parseInt(str[1]), Integer.parseInt(str[2]));
							t.setDepartureDate(dep);
							System.out.println("Please enter no of passengers:");
							int noOfPass=s.nextInt();
							System.out.println("Please enter passenger details:");
							List<Passenger> pass=new ArrayList<>();
							for(int i=0;i<noOfPass;i++)
							{
								System.out.println("For passenger:"+(i+1));
								System.out.println("Please enter first name,last name, email, gender");
								String fname=s.next();
								String lname=s.next();
								String email=s.next();
								String gender=s.next();
								System.out.println("Please enter address:country,state,city,street,unit,zipcode");
								Address a2=new Address();
								a2.setCountry(s.next());
								a2.setState(s.next());
								a2.setCity(s.next());
								a2.setStreet(s.next());
								a2.setUnit(s.nextInt());
								a2.setZipCode(s.nextInt());
								//p.setAddress(a2);
								System.out.println("Please enter passport number,visatype,tickettype and meal preference");
								String passportNo=s.next();
								String visaType=s.next();
								String ticketType=s.next();
								String mealType=s.next();
								Passenger passenger=new Passenger(fname, lname, a2, gender, null, email, null, passportNo, visaType, ticketType, mealType);
								
							}
							System.out.println(t.getArrivalDate());
							System.out.println("Please enter source and destination");
							t.setSourceAirport(s.next());
							t.setDestinationAirpoty(s.next());
							AvailableTransport at=v.viewDisplayList(t);
							System.out.println("The flights suitable for you are:");
							for(int i=0;i<at.getAvailList().size();i++)
							{
								System.out.println(i+1+")"+at.getAvailList().get(i).getAircraft());
							}
							System.out.println("Select your choice:");
							int choice2=s.nextInt()-1;
							
							System.out.println("Please enter Credit/Debit card details for payment: CardType,CardNo,NameOnCard,CVV,ExpiryDate(yyyy-mm-dd)");
							Payment pay=new Payment();
							pay.setCardType(s.next());
							s.nextInt();
							pay.setNameOnCard(s.next());
							pay.setCvv(s.nextInt());
							Calendar arr=Calendar.getInstance();
							String[] str2=s.next().split("-");
							arr.set(Integer.parseInt(str2[0]), Integer.parseInt(str2[1]), Integer.parseInt(str2[2]));
							pay.setExpirtDate(arr.getTime());
							//pay.setBillingAddress(c.getAddress());
							
							Transportation transportation=at.getAvailList().get(choice2);
							SelectedTransport st=new SelectedTransport();
							st.addSelectedTransport(transportation);
							Reservation r=new Reservation();
							r.setTransport(st);
							r.setCustomer(new Customer("Santosh", "ALN", null, "M", null, "aln.santosh@gmail.com", "123456"));
							int id=v.viewConfirmReservation(r);
							
							System.out.println("Successfully booked the flight with reservation ID:"+id);
						
						}
						else if(choice==2)
						{
							System.out.println("Please enter the Reservation ID");
							int id=s.nextInt();
							if(v.d.deleteReservationtInDb(id))
							{
								System.out.println("Reservation has been cancelled");
							}
							else
							{
								System.out.println("Please enter a valid reservation Id to cancel the reservation");
							}
						}
						else if(choice==3)
						{
							System.out.println("The passengers present in the database are:");
							List<Passenger> lp=v.viewQueryPassenger();
							for(int i=0;i<lp.size();i++)
							{
								Passenger p=lp.get(i);
								System.out.print("Passenger "+(i+1)+":");
								System.out.print("\tFirstName: "+p.getFirstName());
								System.out.print("\tLastName: "+p.getLastName());
								System.out.print("\tEmail: "+p.getEmail());
								System.out.print("\tGender: "+p.getGender());
								System.out.print("\tPassportNo: "+p.getPassportNo());
								System.out.print("\tTicketType: "+p.getTicketType());
								System.out.print("\tVisaType: "+p.getVisaType());
								System.out.println("\tAddress: "+p.getAddress().getUnit()+","+p.getAddress().getStreet()+","+p.getAddress().getCity()+","+p.getAddress().getState()+","+p.getAddress().getCountry()+","+p.getAddress().getZipCode());
							}
							try {
								Thread.sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						else if(choice==4)
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
			else if(input==4)
			{
				System.exit(0);
			}
			else
			{
				System.out.println("You have made wrong choice. Please make a valid choice between 1 and 2\n");
				
			}
		}
	}
	
	

}
