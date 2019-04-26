package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionBdd {
	private static Connection connect = null;
	private static String ip = "127.0.0.1";
	private static String bdd = "maven";
	private static String user = "root";
	private static String password = "";
	

	private ConnectionBdd() {
		connect = null;
		try {
			
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			connect = DriverManager.getConnection(
					"jdbc:mysql://"+ip+":3306/"+bdd+"?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC",user,password);
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	
	}

	public static Connection getInstance() {
		if (connect == null) {
			new ConnectionBdd();
		}
		return connect;
	}
}
