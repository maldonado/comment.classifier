package ca.com.evermal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProjectDao {
	
	private Connection connection;
	
	public ProjectDao(Connection connection) {
		this.connection = connection;
	}
	
	public ArrayList<String> getProjects(){
		ArrayList<String> result = new ArrayList<String>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT(projectName) FROM comment_class order by projectName");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				result.add(resultSet.getString("projectName"));
			}
			return result;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}
}
