package com.flight.dao;//Packages

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.flight.bean.Address;
import com.flight.bean.AvailableTransport;
import com.flight.bean.Customer;
import com.flight.bean.Passenger;
import com.flight.bean.Person;
import com.flight.bean.Reservation;
import com.flight.bean.Transportation;
import com.flight.entity.AddressEntity;
import com.flight.entity.CustomerEntity;
import com.flight.entity.PassengerEntity;
import com.flight.entity.PersonEntity;

public class Database {
	
	private static SessionFactory factory;
	
	public static synchronized SessionFactory getDBTable()
	{
		if(factory==null)
		{
			factory=new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	public List<Reservation> addReservationtToDb(Reservation reservation)
	{
		
		return null;
	}
	@SuppressWarnings("finally")
	public boolean addCustomerToDb(Customer customer)
	{
		try{
			factory=getDBTable();
			Session session=factory.getCurrentSession();
			session.beginTransaction();
			CustomerEntity ce=new CustomerEntity();
			ce.setDate(customer.getDate());
			ce.setEmail(customer.getEmail());
			ce.setFirstName(customer.getFirstName());
			ce.setGender(customer.getGender());
			ce.setLastName(customer.getLastName());
			ce.setNoOfReservation(customer.getNoOfReservation());
			ce.setPassword(customer.getPassword());
			List<Passenger> pass=customer.getPassenger();
			List<PassengerEntity> passentity=new ArrayList<>();
			if(pass!=null)
			{
				for(int i=0;i<pass.size();i++)
				{
					Passenger p=pass.get(i);
					PassengerEntity pe=new PassengerEntity();
					pe.setDate(p.getDate());
					pe.setEmail(p.getEmail());
					pe.setFirstName(p.getFirstName());
					pe.setGender(p.getGender());
					pe.setLastName(p.getLastName());
					pe.setMealType(p.getMealType());
					pe.setPassportNo(p.getPassportNo());
					pe.setPassword(p.getPassword());
					pe.setTicketType(p.getTicketType());
					pe.setVisaType(p.getVisaType());
					
					AddressEntity ae=new AddressEntity();
					ae.setCity(p.getAddress().getCity());
					ae.setCountry(p.getAddress().getCountry());
					ae.setState(p.getAddress().getState());
					ae.setStreet(p.getAddress().getStreet());
					ae.setUnit(p.getAddress().getUnit());
					ae.setZipCode(p.getAddress().getZipCode());
					pe.setAddress(ae);
					passentity.add(pe);	
				}
			}
			ce.setPassenger(passentity);
			
			AddressEntity ae=new AddressEntity();
			ae.setCity(customer.getAddress().getCity());
			ae.setCountry(customer.getAddress().getCountry());
			ae.setState(customer.getAddress().getState());
			ae.setStreet(customer.getAddress().getStreet());
			ae.setUnit(customer.getAddress().getUnit());
			ae.setZipCode(customer.getAddress().getZipCode());
			
			ce.setAddress(ae);
			
			session.save(ae);
			session.save(ce);
			
			session.getTransaction().commit();
			
			
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
		return false;
		
	}
	public boolean checkCustomerInDb(Customer customer)
	{
		try
		{
			factory=getDBTable();
			Session session=factory.getCurrentSession();
			session.beginTransaction();
			CustomerEntity ce=session.get(CustomerEntity.class,customer.getEmail());
			if(ce!=null)
			{
				return true;
			}
			else
			{
				return false;
			}
					
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			factory.close();
		}
		return false;
	}
	public AvailableTransport getFlightListFromDb(List<Transportation> transports)
	{
		return null;
	}
	public boolean signIn(Person person)
	{
//		PersonEntity pe=new PersonEntity();
//		pe.setEmail(person.getEmail());
//		pe.setPassword(person.getPassword());
		factory=getDBTable();
		Session session=factory.getCurrentSession();
		session.beginTransaction();
		PersonEntity pe=session.get(PersonEntity.class,person.getEmail());
		if(pe!=null)
		{
			Address a=new Address();
			a.setCity(pe.getAddress().getCity());
			a.setCountry(pe.getAddress().getCountry());
			a.setState(pe.getAddress().getState());
			a.setStreet(pe.getAddress().getStreet());
			a.setUnit(pe.getAddress().getUnit());
			a.setZipCode(pe.getAddress().getUnit());
			person.setAddress(a);
			person.setDate(pe.getDate());
			person.setFirstName(pe.getFirstName());
			person.setGender(pe.getGender());
			
			return (pe.getPassword()==person.getPassword());
		}
		else
		{
			return false;
		}
		
	}
}
