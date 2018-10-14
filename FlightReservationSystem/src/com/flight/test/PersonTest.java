package com.flight.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.flight.bean.Address;
import com.flight.bean.Person;
import com.flight.entity.AddressEntity;
import com.flight.entity.PersonEntity;


public class PersonTest {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{
			System.out.println("Creating Flight object");
			AddressEntity a=new AddressEntity("Glenwood",6,"Boulder", "CO", "USA", 80304);
			PersonEntity p=new PersonEntity("afirst", "blast", a, "M", new Date(2017,2, 11),"abcd122@gmail.com", "123456");
			System.out.println("Beginning Transaction");
			session.beginTransaction();
			System.out.println("Saving the flight");
			session.save(a);
			session.save(p);
			session.getTransaction().commit();
			System.out.println("Commit completed");
			
			//primary id
			System.out.println("Flight Id:"+p.getEmail());
			 //get a session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			PersonEntity f=session.get(PersonEntity.class,p.getEmail());
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
