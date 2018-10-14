package com.flight.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name="customer")
@PrimaryKeyJoinColumn(name="email")
public class CustomerEntity extends PersonEntity {
	
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
			name="passenger_customer",
			joinColumns={@JoinColumn(name="cemail")},
			inverseJoinColumns={@JoinColumn(name="pemail")}
			)
	//@OneToMany(fetch=FetchType.LAZY,mappedBy="customer")
	private List<PassengerEntity> passenger=new ArrayList<>();
	@Column(name="noofreservations")
	private int noOfReservation;
	public List<PassengerEntity> getPassenger() {
		return passenger;
	}
	public void setPassenger(List<PassengerEntity> passenger) {
		this.passenger = passenger;
	}
	public int getNoOfReservation() {
		return noOfReservation;
	}
	public void setNoOfReservation(int noOfReservation) {
		this.noOfReservation = noOfReservation;
	}
	
	
	

}
