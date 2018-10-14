package com.flight.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.flight.entity.AddressEntity;
import com.flight.entity.CustomerEntity;
import com.flight.entity.PassengerEntity;
import com.flight.entity.PersonEntity;

public class CustomerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{
			System.out.println("Creating Customer object");
			AddressEntity a=new AddressEntity("Glenwood",6,"Boulder", "CO", "USA", 80304);
			PersonEntity p=new PersonEntity("Santosh", "ALN", a, "M", new Date(2017,2, 11),"aln.santosh@gmail.com", "123456");
			
			CustomerEntity ce=new CustomerEntity();
			ce.setFirstName(p.getFirstName());
			ce.setLastName(p.getLastName());
			ce.setAddress(p.getAddress());
			ce.setDate(p.getDate());
			ce.setEmail(p.getEmail());
			ce.setGender(p.getGender());
			ce.setPassword(p.getPassword());
			ce.setNoOfReservation(1);
			List<PassengerEntity> listpe=new ArrayList<>();
			for(int i=0;i<ce.getNoOfReservation();i++)
			{
				PassengerEntity pe=new PassengerEntity();
				pe.setFirstName(p.getFirstName());
				pe.setLastName(p.getLastName());
				pe.setAddress(p.getAddress());
				pe.setDate(p.getDate());
				pe.setEmail("asdfz12@gmail.com");
				pe.setGender(p.getGender());
				pe.setPassword(p.getPassword());
				pe.setMealType("Vegeterian");
				pe.setPassportNo("Z13212Z");
				pe.setTicketType("Eco");
				pe.setVisaType("F1");
				listpe.add(pe);
				
			}
			ce.setPassenger(listpe);
			
			System.out.println("Beginning Transaction");
			session.beginTransaction();
			System.out.println("Saving the flight");
			session.save(a);
			//session.save(p);
			//session.save(pe);
			session.save(ce);
			session.getTransaction().commit();
			System.out.println("Commit completed");
			
			//primary id
			System.out.println("Flight Id:"+ce.getEmail());
			 //get a session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			CustomerEntity f=session.get(CustomerEntity.class,ce.getEmail());
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
