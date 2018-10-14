package com.flight.entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.flight.bean.Address;

@Entity
@Table(name="person")
@Inheritance(strategy=InheritanceType.JOINED)
public class PersonEntity {
	private String firstName;
	private String lastName;
	@OneToOne
	@JoinColumn(name="id",nullable=false)
	private AddressEntity address;
	private String gender;
	@Temporal(TemporalType.DATE)
	private Date dob;
	@Id
	private String email;
	private String password;
	
	public PersonEntity()
	{
		
	}
	
	public PersonEntity(String firstName, String lastName, AddressEntity address, String gender, Date date,
			String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.gender = gender;
		this.dob  = date;
		this.email = email;
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AddressEntity getAddress() {
		return address;
	}

	public void setAddress(AddressEntity address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDate() {
		return dob;
	}

	public void setDate(Date date) {
		this.dob = date;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
