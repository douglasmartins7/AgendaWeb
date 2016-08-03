package br.com.caelum.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws ClassNotFoundException {
	     try {
	    	 Class.forName( "com.mysql.jdbc.Driver" );
	         return DriverManager.getConnection(
	        		 "jdbc:mysql://localhost:3306/fj21", "root", "root");
	     } catch (SQLException e) {
	         throw new RuntimeException(e);
	     }
	 }
}
