//package com.comcast.crm.generic.databaseutility;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//public class DatabaseUtility {
//	
//	Connection conn;
//	
//	public void getDBConnection(String url, String username, String password)
//	{
//		try
//		{
//		Driver driverref = new Driver();
//		DriverManager.registerDriver(driverref);
//		conn = DriverManager.getConnection(url, username, password);
//		}
//		catch(Exception e)
//		{
//		}
//	}
//	
//	//Hardcode url, username,password(Because only one database will be there for an application)
//	public void getDBConnection()
//	{
//		try
//		{
//		Driver driverref = new Driver();
//		DriverManager.registerDriver(driverref);
//		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root","root");
//		}
//		catch(Exception e)
//		{
//		}
//	}
//	
//	public void closeDBConnection() 
//	{
//		try
//		{
//		conn.close();
//		}
//		catch(Exception e)
//		{
//		}
//	}
//	
//	public ResultSet executeSelectQuery(String query)
//	{
//		ResultSet result=null;
//		try
//		{
//		Statement stat = conn.createStatement();
//		result = stat.executeQuery(query);
//		}
//		catch(Exception e)
//		{
//		}
//		return result;
//		}
//	public int executeNonSelectQuery(String query)
//{
//	int result = 0;
//	try
//	{
//	Statement stat = conn.createStatement();
//	result = stat.executeUpdate(query);
//	}
//	catch(Exception e)
//	{
//	}
//	return result;
//	}
//}
//
//
