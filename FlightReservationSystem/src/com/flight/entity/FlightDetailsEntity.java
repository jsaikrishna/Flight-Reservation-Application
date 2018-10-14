package com.flight.entity;

import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Table(name="flight_details")
public class FlightDetailsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="airline")
	private String airline;
	@Column(name="aircraft")
	private String aircraft;
	@Column(name="vesselno")
	private String vesselNo;
	@Column(name="sourceairport")
	private String sourceAirport;
	@Column(name="destinationairport")
	private String destinationAirpoty;
	@Column(name="arrivaldate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar arrivalDate;
	@Column(name="departuredate")
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar departureDate;
	@Column(name="modelname")
	private String modelName;
	@Column(name="noofseats")
	private int noOfSeats;
	@Column(name="seatsbooked")
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
