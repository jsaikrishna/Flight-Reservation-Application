package com.flight.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

//import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.flight.bean.Address;
import com.flight.bean.AvailableTransport;
import com.flight.bean.Customer;
import com.flight.bean.Passenger;
import com.flight.bean.Person;
import com.flight.bean.Reservation;
import com.flight.bean.SelectedTransport;
import com.flight.bean.Transportation;
import com.flight.entity.AddressEntity;
import com.flight.entity.CustomerEntity;
import com.flight.entity.FlightDetailsEntity;
import com.flight.entity.PassengerEntity;
import com.flight.entity.PersonEntity;
import com.flight.entity.ReservationEntity;
import com.flight.entity.SelectedTransportEntity;
import com.flight.entity.TransportationEntity;

public class Database {
	
	private static SessionFactory factory;
	private static Session session;
	public static synchronized SessionFactory getDBTable()
	{
		if(factory==null)
		{
			factory=new Configuration().configure().buildSessionFactory();
			//session=factory.getCurrentSession();
			//session.beginTransaction();
		}
		if(session==null)
			session=factory.getCurrentSession();
//		if(!session.isConnected())
//			session=factory.getCurrentSession();
		if(!session.getTransaction().isActive())
		{
			session=factory.getCurrentSession();
			session.beginTransaction();
		}
		return factory;
	}
	public int addReservationtToDb(Reservation reservation)
	{
		try
		{
			factory=getDBTable();
			//Session session=factory.getCurrentSession();
			//session.beginTransaction();
			ReservationEntity re=new ReservationEntity();
			CustomerEntity ce=session.get(CustomerEntity.class,reservation.getCustomer().getEmail());
			if(ce!=null)
			{
				SelectedTransportEntity ste=new SelectedTransportEntity();
//				TransportationEntity te=new TransportationEntity();
//				te.setAircraft(reservation.getTransport().getAircraft());
//				te.setAirline(reservation.getTransport().getAirline());
//				te.setArrivalDate(reservation.getTransport().getArrivalDate());
//				te.setDepartureDate(reservation.getTransport().getDepartureDate());
//				te.setDestinationAirpoty(reservation.getTransport().getDestinationAirpoty());
//				te.setModelName(reservation.getTransport().getModelName());
//				te.setNoOfSeats(reservation.getTransport().getNoOfSeats());
//				te.setSeatsBooked(reservation.getTransport().getSeatsBooked());
//				te.setSourceAirport(reservation.getTransport().getSourceAirport());
//				te.setVesselNo(reservation.getTransport().getVesselNo());
				
//				Customer c=reservation.getCustomer();
//				CustomerEntity ce=new CustomerEntity();
//				ce.set
//				
				Query query=session.createQuery("select max(id)from ReservationEntity");
				List q=query.list();
				int id=0;
				if(q.get(0)!=null)
					id=(int)q.get(0);
				List<TransportationEntity> lte=new ArrayList<>();
				List<Transportation> lt=reservation.getTransport().getSelectedList();
				for(int i=0;i<lt.size();i++)
				{
					TransportationEntity te=new TransportationEntity();
					te.setAircraft(lt.get(i).getAircraft());
					te.setAirline(lt.get(i).getAirline());
					te.setArrivalDate(lt.get(i).getArrivalDate());
					te.setDepartureDate(lt.get(i).getDepartureDate());
					te.setDestinationAirpoty(lt.get(i).getDestinationAirpoty());
					te.setModelName(lt.get(i).getModelName());
					te.setNoOfSeats(lt.get(i).getNoOfSeats());
					te.setSeatsBooked(lt.get(i).getSeatsBooked());
					te.setSourceAirport(lt.get(i).getSourceAirport());
					te.setVesselNo(lt.get(i).getVesselNo());
					lte.add(te);
				}
				ste.setSelectedList(lte);
				re.setCustomer(ce);
				re.setTransport(ste);
				session.save(ste);
				
				session.save(re);
				
				Query que=session.createQuery("update FlightDetailsEntity set noOfSeats=noOfSeats-1 where aircraft='"+re.getTransport().getSelectedList().get(0).getAircraft()+"'");
				int status=que.executeUpdate();
				session.getTransaction().commit();
				
				return (id+1);
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//factory.close();
			factory=null;
			session=null;
		}
		
		return 0;
	}
	
	public boolean deleteReservationtInDb(int rid)
	{
		try
		{
			factory=getDBTable();
			//Session session=factory.getCurrentSession();
			//session.beginTransaction();
			ReservationEntity re=session.get(ReservationEntity.class,rid);
			if(re!=null)
			{
				Query query=session.createQuery("delete from ReservationEntity where id="+rid);
				int status=query.executeUpdate();
				session.getTransaction().commit();
				return true;
				
			}
			return false;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//factory.close();
			factory=null;
			session=null;
		}
		
		return false;
	}
	
	public Reservation getReservationDetails(int rid)
	{
		try
		{
			factory=getDBTable();
			//Session session=factory.getCurrentSession();
			//session.beginTransaction();
			ReservationEntity re=session.get(ReservationEntity.class,rid);
			if(re!=null)
			{
				Reservation r=new Reservation();
				Customer c=new Customer();
				c.setEmail(re.getCustomer().getEmail());
				r.setCustomer(c);
				SelectedTransport st=new SelectedTransport();
				List<TransportationEntity> lste=re.getTransport().getSelectedList();
                List<Transportation> list = lste.stream().map(te -> {
                	Transportation transportation = new Transportation();
                	transportation.setDepartureDate(te.getDepartureDate());
                	transportation.setId(te.getId());
                	return transportation;
                }).collect(Collectors.toList());
                st.setSelectedList(list);
                r.setTransport(st);
                return r;
				
				
			}
			return null;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//factory.close();
			factory=null;
			session=null;
		}
		
		return null;
	}
	
	public boolean updateReservationtInDb(int rid) ///have to complete this function
	{
		try
		{
			factory=getDBTable();
			//Session session=factory.getCurrentSession();
			//session.beginTransaction();
			ReservationEntity re=session.get(ReservationEntity.class,rid);
			if(re!=null)
			{
				Query query=session.createQuery("update ReservationEntity set  where id="+rid);
				int status=query.executeUpdate();
				session.getTransaction().commit();
				return true;
				
			}
			return false;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//factory.close();
			factory=null;
			session=null;
		}
		
		return false;
	}
	
	
	@SuppressWarnings("finally")
	public boolean addCustomerToDb(Customer customer)
	{
		try{
			factory=getDBTable();
			Session session=factory.getCurrentSession();
			//session.beginTransaction();
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
			//factory.close();
			factory=null;
			session=null;
		}
		return false;
		
	}
	public boolean checkCustomerInDb(Customer customer)
	{
		try
		{
			factory=getDBTable();
			//Session session=factory.getCurrentSession();
			//session.beginTransaction();
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
			//factory.close();
			factory=null;
			session=null;
		}
		return false;
	}
	public AvailableTransport getFlightListFromDb(Transportation transport)
	{
		factory=getDBTable();
		//Session session=factory.getCurrentSession();
		//session.beginTransaction();
		Calendar c=Calendar.getInstance();
		//Query query=session.createQuery("from transportation where departuredate>="+departureDate);
		//org.hibernate.Query q
		//List<Transportation> list=query.l
		//Query query=session.createQuery("from TransportationEntity where departureDate>=:depdate and sourceAirport='"+transport.getSourceAirport()+"'and destinationAirpoty='"+transport.getDestinationAirpoty()+"'");//+transport.getDepartureDate());
		Query query=session.createQuery("from FlightDetailsEntity where departureDate>=:depdate and sourceAirport='"+transport.getSourceAirport()+"'and destinationAirpoty='"+transport.getDestinationAirpoty()+"'");
		query.setDate("depdate", transport.getDepartureDate().getTime());
		List<FlightDetailsEntity> list1=query.list();
		System.out.println(list1.get(0).getAircraft());
		AvailableTransport at=new AvailableTransport();
		for(int i=0;i<list1.size();i++)
		{
			FlightDetailsEntity te=list1.get(i);
			if(te.getNoOfSeats()==0)
				continue;
			Transportation t=new Transportation();
			t.setAircraft(te.getAircraft());
			t.setAirline(te.getAirline());
			t.setArrivalDate(te.getArrivalDate());
			t.setDepartureDate(te.getDepartureDate());
			t.setDestinationAirpoty(te.getDestinationAirpoty());
			t.setModelName(te.getModelName());
			t.setNoOfSeats(te.getNoOfSeats());
			t.setSourceAirport(te.getSourceAirport());
			t.setVesselNo(te.getVesselNo());
			t.setId(te.getId());
			at.addAvailTransport(t);
			
		}
		factory=null;
		session=null;
		return at;
	}
	public Customer signIn(Person person)
	{
//		PersonEntity pe=new PersonEntity();
//		pe.setEmail(person.getEmail());
//		pe.setPassword(person.getPassword());
		try
		{
			
		
			factory=getDBTable();
			Session session=factory.getCurrentSession();
			//session.beginTransaction();
			PersonEntity pe=session.get(PersonEntity.class,person.getEmail());
			CustomerEntity ce=session.get(CustomerEntity.class, person.getEmail());
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
				
				Customer c=new Customer();
				c.setAddress(a);
				c.setDate(ce.getDate());
				c.setEmail(ce.getEmail());
				c.setFirstName(ce.getFirstName());
				c.setGender(ce.getGender());
				c.setLastName(ce.getLastName());
				c.setNoOfReservation(ce.getNoOfReservation());
	/*			List<PassengerEntity> lpe=ce.getPassenger();
				for(int i=0;i<ce.getPassenger().size();i++)
				{
					PassengerEntity passe=lpe.get(i);
					Passenger p=new Passenger();
					p.setAddress(address);
				} 
	*/			c.setPassword(ce.getPassword());
				
				return c;//(pe.getPassword()==person.getPassword());
			}
			else
			{
				return null;
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally
		{
			//factory.close();
			factory=null;
			session=null;
		}
		return null;
		
	}
	public List<Passenger> DisplayPassengers()
	{
		try
		{
			factory=getDBTable();
			//Session session=factory.getCurrentSession();
			//session.beginTransaction();
			
			Query query=session.createQuery("from PassengerEntity");
			List q=query.list();
			List<Passenger> lp=new ArrayList<>();
			for(int i=0;i<q.size();i++)
			{
				PassengerEntity pe=(PassengerEntity)q.get(i);
				Passenger p=new Passenger();
				p.setDate(pe.getDate());
				p.setEmail(pe.getEmail());
				p.setFirstName(pe.getFirstName());
				p.setGender(pe.getGender());
				p.setLastName(pe.getLastName());
				p.setMealType(pe.getMealType());
				p.setPassportNo(pe.getPassportNo());
				p.setTicketType(pe.getTicketType());
				p.setVisaType(pe.getVisaType());
				Address a=new Address();
				a.setCity(pe.getAddress().getCity());
				a.setCountry(pe.getAddress().getCountry());
				a.setState(pe.getAddress().getState());
				a.setStreet(pe.getAddress().getStreet());
				a.setUnit(pe.getAddress().getUnit());
				a.setZipCode(pe.getAddress().getZipCode());
				p.setAddress(a);
				lp.add(p);
				
			}
			session.getTransaction().commit();
			return lp;
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			//factory.close();
			factory=null;
			session=null;
		}
		
		return null;
	}
	public void returnAvailableSeats(Transportation transportation) {
		// TODO Auto-generated method stub
		try
		{
			factory=getDBTable();
			System.out.println("Transportation:"+transportation.getId());//need to get an ID from 
			FlightDetailsEntity te=session.get(FlightDetailsEntity.class,transportation.getId());
			System.out.println(te.getId()+":ID");
			if(te.getSeatsBooked().equals(""))
			{
				System.out.println("Please enter the seats that you want to book between 1 and 10");
				Scanner s=new Scanner(System.in);
				String inp=s.next();
				te.setSeatsBooked(inp);
				session.save(te);
				session.getTransaction().commit();
			}
			else
			{
				StringTokenizer st=new StringTokenizer(te.getSeatsBooked(), ",");
				List<Integer> li=new ArrayList<>();
				String output="";
				while(st.hasMoreTokens())
				{
					String str=st.nextToken();
					li.add(Integer.parseInt(str));
				}
				if(li.size()==10)
				{
					System.out.println("The custom booking seats have been reserved. We cannot assigned you the desired seat");
				}
				else
				{
					for(int i=0;i<10;i++)
					{
						if(!li.contains(i))
						{
							output+=""+i+" ";
						}
					}
					System.out.println("Please select any of the foll	owing seats:");
					Scanner s=new Scanner(System.in);
					String seat=s.next();
					te.setSeatsBooked(te.getSeatsBooked()+","+seat);
					session.save(te);
					session.getTransaction().commit();
					
				}
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			factory=null;
			session=null;
		}
		
	}
}
