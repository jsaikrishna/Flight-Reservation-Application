package com.flight.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.flight.bean.Transportation;

@Entity
@Table(name="selectedtransport")
public class SelectedTransportEntity{
	@ManyToMany(cascade={CascadeType.ALL})
	@JoinTable(
			name="selected_transportation",
			joinColumns={@JoinColumn(name="sid")},
			inverseJoinColumns={@JoinColumn(name="tid")}
			)
	List<TransportationEntity> selectedList=new ArrayList<>();
	@Id
	@Column(name="stid")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int stid;
	public List<TransportationEntity> getSelectedList() {
		return selectedList;
	}
	public void setSelectedList(List<TransportationEntity> selectedList) {
		this.selectedList = selectedList;
	}
	public int getStid() {
		return stid;
	}
	public void setStid(int stid) {
		this.stid = stid;
	}
	
	

}
