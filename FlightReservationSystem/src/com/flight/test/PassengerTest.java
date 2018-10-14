package com.flight.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.flight.entity.AddressEntity;
import com.flight.entity.PassengerEntity;
import com.flight.entity.PersonEntity;

public class PassengerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{
			System.out.println("Creating Passenger object");
			AddressEntity a=new AddressEntity("Glenwood",6,"Boulder", "CO", "USA", 80304);
			PersonEntity p=new PersonEntity("afirst", "blast", a, "M", new Date(2017,2, 11),"abcde122@gmail.com", "123456");
			PassengerEntity pe=new PassengerEntity();
			pe.setFirstName(p.getFirstName());
			pe.setLastName(p.getLastName());
			pe.setAddress(p.getAddress());
			pe.setDate(p.getDate());
			pe.setEmail(p.getEmail());
			pe.setGender(p.getGender());
			pe.setPassword(p.getPassword());
			pe.setMealType("Vegeterian");
			pe.setPassportNo("Z13212Z");
			pe.setTicketType("Eco");
			pe.setVisaType("F1");
			
			System.out.println("Beginning Transaction");
			session.beginTransaction();
			System.out.println("Saving the flight");
			session.save(a);
			//session.save(p);
			session.save(pe);
			session.getTransaction().commit();
			System.out.println("Commit completed");
			
			//primary id
			System.out.println("Flight Id:"+pe.getEmail());
			 //get a session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			PassengerEntity f=session.get(PassengerEntity.class,pe.getEmail());
			System.out.println("Person:"+f);
			System.out.println(f.getFirstName()+f.getLastName()+f.getEmail()+f.getPassword());
			session.getTransaction().commit();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			factory.close();
		}

	}

}
