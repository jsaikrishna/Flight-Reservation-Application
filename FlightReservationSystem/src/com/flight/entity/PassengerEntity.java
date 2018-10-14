package com.flight.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import com.flight.bean.Address;


@Entity
@Table(name="passenger")
@PrimaryKeyJoinColumn(name="email")
public class PassengerEntity extends PersonEntity {
	@Column(name="passportno")
	private String passportNo;
	@Column(name="visatype")
	private String visaType;
	@Column(name="tickettype")
	private String ticketType;
	@Column(name="mealtype")
	private String mealType;
//	@ManyToOne(fetch=FetchType.LAZY)
//	@JoinColumn(name="email")
	//private CustomerEntity customer=new CustomerEntity();
	
	
//	public CustomerEntity getCustomer() {
//		return customer;
//	}
//	public void setCustomer(CustomerEntity customer) {
//		this.customer = customer;
//	}
	public String getPassportNo() {
		return passportNo;
	}
	public void setPassportNo(String passportNo) {
		this.passportNo = passportNo;
	}
	public String getVisaType() {
		return visaType;
	}
	public void setVisaType(String visaType) {
		this.visaType = visaType;
	}
	public String getTicketType() {
		return ticketType;
	}
	public void setTicketType(String ticketType) {
		this.ticketType = ticketType;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}

}
