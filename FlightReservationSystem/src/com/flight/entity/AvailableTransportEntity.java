package com.flight.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.flight.bean.Transportation;

@Entity
@Table(name="availabletransportentity")
@PrimaryKeyJoinColumn(name="id")
public class AvailableTransportEntity extends TransportationEntity{
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
			name="transportation_available",
			joinColumns={@JoinColumn(name="aid")},
			inverseJoinColumns={@JoinColumn(name="sid")}
			)
	private List<Transportation> availList=new ArrayList<>();

	public List<Transportation> getAvailList() {
		return availList;
	}

	public void setAvailList(List<Transportation> availList) {
		this.availList = availList;
	}
	
	

}
