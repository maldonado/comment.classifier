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
	
	public int getClassificationProgress(String projectName){
		String totalCommentsSQL = "select count(*) as total from processed_comment a, comment_class b where a.commentclassid = b.id  and b.projectname = ?";
		String classifiedCommentsSQL = "select count(*) as classified from processed_comment a, comment_class b where a.commentclassid = b.id  and b.projectname = ? and a.classification is not null";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(totalCommentsSQL);
			preparedStatement.setString(1, projectName);
			ResultSet resultSet = preparedStatement.executeQuery();
			float total = 0;
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
			PreparedStatement preparedStatement2 = connection.prepareStatement(classifiedCommentsSQL);
			preparedStatement2.setString(1, projectName);
			ResultSet resultSet2 = preparedStatement2.executeQuery();
			float classified = 0;
			while(resultSet2.next()){
				classified = resultSet2.getInt("classified");
			}
			if(classified == 0){
				return 0;
			}else{
				
				float percentage = (classified * 100 / total);
				return (int) Math.round(percentage);	
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return 0;
	}
}
