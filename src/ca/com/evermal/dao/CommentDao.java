package ca.com.evermal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ca.com.evermal.model.Comment;

public class CommentDao {

	private Connection connection;

	public CommentDao(Connection connection) {
		this.connection = connection;
	}

	public Comment getCommentsToClassifyByProject(String projectName) {
		String sql = "select a.id,a.commentclassid,a.startline,a.endline,a.commenttext,a.type,a.location,a.description,a.dictionary_hit,a.jdeodorant_hit,a.refactoring_list_name ,a.classification, b.projectName from processed_comment a, comment_class b where a.commentclassid = b.id  and b.projectname = ? and a.classification is null order by a.id limit 1";
		return fetchComments(projectName, sql);
	}

	public Comment getAllCommentsByProject(String projectName) {
		String sql = "select a.id,a.commentclassid,a.startline,a.endline,a.commenttext,a.type,a.location,a.description,a.dictionary_hit,a.jdeodorant_hit,a.refactoring_list_name ,a.classification,b.projectName from processed_comment a,  comment_class b where a.commentclassid = b.id  and b.projectname = ? order by a.is limit 1";
		return fetchComments(projectName, sql);
	}

	public Comment findPreviousById(long commentId, String projectName) {
		String sql = "select a.id, a.commentclassid, a.startline, a.endline, a.commenttext, a.type, a.location,a.description,a.dictionary_hit,a.jdeodorant_hit,a.refactoring_list_name ,a.classification,b.projectName  from processed_comment a, comment_class b where a.commentclassid = b.id and b.projectname = ? and  a.id < ? order by a.id  desc  limit 1";
		Comment comment = new Comment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, projectName);
			preparedStatement.setLong(2, commentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
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
				comment.setProjectName(resultSet.getString("projectName"));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public Comment findById(long commentId) {
		String sql = "select a.id,a.commentclassid,a.startline,a.endline,a.commenttext,a.type,a.location,a.description,a.dictionary_hit,a.jdeodorant_hit,a.refactoring_list_name ,a.classification,b.projectName from processed_comment a, comment_class b where a.commentclassid = b.id  and a.id = ? ";
		Comment comment = new Comment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, commentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
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
				comment.setProjectName(resultSet.getString("projectName"));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
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
			updateCommentWithTheSameText(comment);
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private void updateCommentWithTheSameText(Comment comment) {
		String sql = "UPDATE processed_comment set classification=? where commenttext =?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, comment.getClassification());
			preparedStatement.setString(2, comment.getText());
			preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

	private Comment fetchComments(String projectName, String sql) {
		Comment comment = new Comment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, projectName);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
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
				comment.setProjectName(resultSet.getString("projectName"));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}

}