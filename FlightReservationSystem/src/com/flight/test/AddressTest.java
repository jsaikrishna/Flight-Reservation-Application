package com.flight.test;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.flight.entity.AddressEntity;
import com.flight.entity.PersonEntity;

public class AddressTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		
		try
		{
			System.out.println("Creating Address object");
			AddressEntity a=new AddressEntity("Glenwood",6,"Boulder", "CO", "USA", 80304);
			System.out.println("Beginning Transaction");
			session.beginTransaction();
			System.out.println("Saving the address");
			session.save(a);
			session.getTransaction().commit();
			System.out.println("Commit completed");
			
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
