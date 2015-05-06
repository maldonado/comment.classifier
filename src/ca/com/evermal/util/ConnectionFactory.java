package ca.com.evermal.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	public Connection getConnection(){
		Connection connection = null;
		String database = "comment_classification";
		String username = "evermal";
		String password = "";
		
		try {
			 Class.forName("org.postgresql.Driver");
	         connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database, username, password);
		} catch ( Exception e ) {
			System.out.println(e);
		}
		return connection;
	}
}