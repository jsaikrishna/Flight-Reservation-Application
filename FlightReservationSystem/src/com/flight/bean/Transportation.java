package com.flight.bean;

import java.util.Calendar;

import com.flight.dao.Database;

public class Transportation {
	private int id;
	private String airline;
	private String aircraft;
	private String vesselNo;
	private String sourceAirport;
	private String destinationAirpoty;
	private Calendar arrivalDate;
	private Calendar departureDate;
	private String modelName;
	private int noOfSeats;
	private String seatsBooked=new String();
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(String seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	public int returnAvailableSeats()
	{
		Database d=new Database();
		return 0;
	}
	
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getAircraft() {
		return aircraft;
	}
	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}
	public String getVesselNo() {
		return vesselNo;
	}
	public void setVesselNo(String vesselNo) {
		this.vesselNo = vesselNo;
	}
	public String getSourceAirport() {
		return sourceAirport;
	}
	public void setSourceAirport(String sourceAirport) {
		this.sourceAirport = sourceAirport;
	}
	public String getDestinationAirpoty() {
		return destinationAirpoty;
	}
	public void setDestinationAirpoty(String destinationAirpoty) {
		this.destinationAirpoty = destinationAirpoty;
	}
	public Calendar getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(Calendar arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	public Calendar getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Calendar departureDate) {
		this.departureDate = departureDate;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public int getNoOfSeats() {
		return noOfSeats;
	}
	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	
	

}
