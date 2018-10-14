package com.flight.test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.flight.entity.AddressEntity;
import com.flight.entity.CustomerEntity;
import com.flight.entity.PassengerEntity;
import com.flight.entity.PersonEntity;
import com.flight.entity.ReservationEntity;
import com.flight.entity.SelectedTransportEntity;
import com.flight.entity.TransportationEntity;

public class TransportationTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SessionFactory factory=new Configuration().configure().buildSessionFactory();
		Session session=factory.getCurrentSession();
		try
		{
			System.out.println("Creating Customer object");
			AddressEntity a=new AddressEntity("Glenwood",6,"Boulder", "CO", "USA", 80304);
			PersonEntity p=new PersonEntity("afirst", "blast", a, "M", new Date(2017,2, 11),"abc123qaz@gmail.com", "123456");
			
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
				pe.setEmail("as1dlalalfqa2@gmail.com");
				pe.setGender(p.getGender());
				pe.setPassword(p.getPassword());
				pe.setMealType("Vegeterian");
				pe.setPassportNo("Z13212Z");
				pe.setTicketType("Eco");
				pe.setVisaType("F1");
				listpe.add(pe);
				
			}
			ce.setPassenger(listpe);
			TransportationEntity te=new TransportationEntity();
			te.setAircraft("BA");
			te.setAirline("BA141");
			Calendar c=Calendar.getInstance();
			c.set(2017, 11, 10);
			te.setArrivalDate(c);
			te.setDepartureDate(c);
			te.setDestinationAirpoty("Cali");
			te.setModelName("141");
			te.setNoOfSeats(10);
			te.setSourceAirport("Denver");
			te.setVesselNo("141");
			te.setSeatsBooked("");
			ReservationEntity re=new ReservationEntity();
			re.setCustomer(ce);
			//re.setTransport(te);
			SelectedTransportEntity ste=new SelectedTransportEntity();
			//ste.setId(10);
			List<TransportationEntity> lte=new ArrayList<>();
			lte.add(te);
			ste.setSelectedList(lte);
			re.setTransport(ste);
			
			System.out.println("Beginning Transaction");
			session.beginTransaction();
			System.out.println("Saving the flight");
			session.save(a);
			//session.save(p);
			//session.save(pe);
			session.save(ce);
			session.save(ste);
			session.save(re);
			session.getTransaction().commit();
			System.out.println("Commit completed");
			
			//primary id
			System.out.println("Flight Id:"+re.getId());
			 //get a session and start transaction
			session=factory.getCurrentSession();
			session.beginTransaction();
			ReservationEntity f=session.get(ReservationEntity.class,re.getId());
			System.out.println("Person:"+f);
			System.out.println(f.getCustomer()+"\n"+f.getTransport());
			//session.getTransaction().commit();	
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
