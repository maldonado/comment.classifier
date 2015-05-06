package ca.com.evermal.dao;

import java.sql.Connection;

public class CommentDao {

	private Connection connection;
	
	public CommentDao(Connection connection) {
		this.connection = connection;
	}
}