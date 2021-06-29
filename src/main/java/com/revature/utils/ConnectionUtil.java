package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//This class contains the logic that manages the connection to our database
//it will have a method called getConnection() that will return Connection objects
//we will need these Connection object in our DAO layer to interact with our database
public class ConnectionUtil {
	
	//a method called getConnection that returns a Connection object
	public static Connection getConnection() throws SQLException {
		
//for compatibility with other technologies/frameworks, we'll need to register our Driver
//this process makes the application aware of what Driver class (what SQL dialect) we are using
		
		try {
			Class.forName("org.postgresql.Driver"); //try to find and return the postgresql driver class
		}catch (ClassNotFoundException e) {
			System.out.println("Class wasn't found :(");
			e.printStackTrace(); //print the exception message to the console
		}
		
		//we need to provide out database credentials
		//we'll hardcode them for now, but we'll see how to hide this username/password in environment variables
		//I've successfully hidden my DB credentials in my environment variables
		//run -> run configurations -> environment -> add the environment variable key/pairs you want
		String url = System.getenv("url"); 
		String username= System.getenv("username");
		String password = System.getenv("password"); //what  your postgres password is 
		
		//This is what returns our actual Connection object (note how this getConnection() method has a return type of Connection
		return DriverManager.getConnection(url, username,password);
		
	}

}