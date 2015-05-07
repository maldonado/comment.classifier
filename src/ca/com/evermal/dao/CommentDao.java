package ca.com.evermal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.com.evermal.model.Comment;

public class CommentDao {

	private Connection connection;

	public CommentDao(Connection connection) {
		this.connection = connection;
	}

	public ArrayList<Comment> getCommentsToClassifyByProject(String projectName) {
		String sql = "select a.* from processed_comment a, comment_class b where a.commentclassid = b.id  and b.projectname = ? and a.classification is null";
		return fetchComments(projectName, sql);
	}

	public ArrayList<Comment> getAllCommentsByProject(String projectName) {
		String sql = "select a.* from processed_comment a, comment_class b where a.commentclassid = b.id  and b.projectname = ?";
		return fetchComments(projectName, sql);
	}

	public void update(Comment comment) {
		String sql = "UPDATE processed_comment set commentClassId=?, startLine=?, endLine=?, commentText=?, type=?, location=?, description=?, dictionary_hit=?, jdeodorant_hit=?, refactoring_list_name=?, classification=? where id =?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, comment.getClassCommentId());
			preparedStatement.setInt(2, comment.getStartLine());
			preparedStatement.setInt(3, comment.getEndLine());
			preparedStatement.setString(4, comment.getText());
			preparedStatement.setString(5, comment.getType());
			preparedStatement.setString(6, comment.getLocation());
			preparedStatement.setString(7, comment.getDescription());
			preparedStatement.setInt(8, comment.getDictionaryHit());
			preparedStatement.setInt(9, comment.getJdeodorantHit());
			preparedStatement.setString(10, comment.getRefactoringListName());
			preparedStatement.setString(11, comment.getClassification());
			preparedStatement.setLong(12, comment.getId());
			preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private ArrayList<Comment> fetchComments(String projectName, String sql) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, projectName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				Comment comment = new Comment();
				comment.setId(resultSet.getLong("id"));
				comment.setClassCommentId(resultSet.getLong("commentClassId"));
				comment.setText(resultSet.getString("commentText"));
				comment.setType(resultSet.getString("type"));
				comment.setLocation(resultSet.getString("location"));
				comment.setDescription(resultSet.getString("description"));
				comment.setStartLineWitoutCorrection(resultSet.getInt("startLine"));
				comment.setEndLineWitoutCorrection(resultSet.getInt("endLine")); 
				comment.setDictionaryHit(resultSet.getInt("dictionary_hit"));
				comment.setJdeodorantHit(resultSet.getInt("jdeodorant_hit"));
				comment.setRefactoringListName(resultSet.getString("refactoring_list_name"));
				comment.setClassification(resultSet.getString("classification"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comments;
	}
}