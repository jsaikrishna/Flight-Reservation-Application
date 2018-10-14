package com.flight.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.flight.bean.Passenger;
import com.flight.bean.SelectedTransport;

@Entity
@Table(name="reservation")
public class ReservationEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rid")
	public int id;
	@OneToOne
	@JoinColumn(name="id", nullable=false)
	public SelectedTransportEntity transport=new SelectedTransportEntity();
	@OneToOne
	@JoinColumn(name="email",nullable=false)
	public CustomerEntity customer=new CustomerEntity();
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public SelectedTransportEntity getTransport() {
		return transport;
	}
	public void setTransport(SelectedTransportEntity transport) {
		this.transport = transport;
	}
	public CustomerEntity getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerEntity customer) {
		this.customer = customer;
	}
	

}
