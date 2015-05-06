package ca.com.evermal.dao;

import java.sql.Connection;
import java.util.List;

public class CommentClassDao {
	
	private Connection connection;
	
	public CommentClassDao(Connection connection) {
		this.connection = connection;
	}

	public List<String> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}
}