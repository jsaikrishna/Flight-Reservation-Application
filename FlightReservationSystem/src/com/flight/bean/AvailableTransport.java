package com.flight.bean;

import java.util.ArrayList;
import java.util.List;

public class AvailableTransport extends Transportation {
	
	private List<Transportation> availList=new ArrayList<>();
	
	public int returnAvailableSeats()
	{
		for(Transportation t: availList)
		{
			t.returnAvailableSeats();
		}
		return 0;
	}
	
	public List<Transportation> getAvailList() {
		return availList;
	}

	public void setAvailList(List<Transportation> availList) {
		this.availList = availList;
	}

	public void searchTransport()
	{
		
	}
	
	public void addAvailTransport(Transportation transport)
	{
		availList.add(transport);
	}
	
	public void removeAvailTransport(Transportation transport)
	{
		availList.remove(transport);
	}
	
	

}
