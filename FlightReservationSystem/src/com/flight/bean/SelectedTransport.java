package com.flight.bean;

import java.util.ArrayList;
import java.util.List;

public class SelectedTransport extends Transportation {
	
	List<Transportation> selectedList=new ArrayList<>();
	
	
	public List<Transportation> getSelectedList() {
		return selectedList;
	}

	public void setSelectedList(List<Transportation> selectedList) {
		this.selectedList = selectedList;
	}

	public int returnAvailableSeats()
	{
		for(Transportation t: selectedList)
		{
			t.returnAvailableSeats();
		}
		
		return 0;
	}
	
	public void selectPreference()
	{
		
	}
	
	public void addSelectedTransport(Transportation transport)
	{
		selectedList.add(transport);
	}
	
	public void removeSelectedTransport(Transportation transport)
	{
		selectedList.remove(transport);
	}
	

}
