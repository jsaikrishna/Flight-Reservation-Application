package com.flight.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String jdbcUrl="jdbc:mysql://localhost:3306/flight_database?useSSL=false";//useSSL is false to get rid of SSL warnings
		String user="santosh";
		String pass="santosh";
		
		try
		{
			System.out.println("Connecting to database:"+jdbcUrl);
			Connection con= DriverManager.getConnection(jdbcUrl,user,pass);
			System.out.println("Connection Successful");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

}
