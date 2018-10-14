package com.flight.bean;

import com.flight.business.service.ViewControl2;

public class Flight extends Transportation {
	
	public int returnAvailableSeats()
	{
		ViewControl2 vc=new ViewControl2();
		vc.viewReturnAvailableSeats((Transportation)this);
		return 0;
	}

}
