package ca.com.evermal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ca.com.evermal.model.SignificativeSampleComment;

public class SignificativeSampleDao {
	
private Connection connection;
	
	public SignificativeSampleDao(Connection connection) {
		this.connection = connection;
	}
	
	public SignificativeSampleComment getSignificativeSampleCommentsToClassifyByReviewer(String reviewerName) {
		String sql = "select processedCommentId, projectName, commenttext, classification, reviewer, reviewerClassification from significative_sample where reviewer = ?  and reviewerClassification is null order by processedCommentId limit 1";
		return fetchComments(reviewerName, sql);
	}
	
	private SignificativeSampleComment fetchComments(String reviewerName, String sql) {
		SignificativeSampleComment comment = new SignificativeSampleComment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reviewerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				comment.setProcessedCommentId(resultSet.getLong("processedCommentId"));
				comment.setProjectName(resultSet.getString("projectName"));
				comment.setText(resultSet.getString("commentText"));
				comment.setClassification(resultSet.getString("classification"));
				comment.setReviewer(resultSet.getString("reviewer"));
				comment.setReviewerClassification(resultSet.getString("reviewerClassification"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public SignificativeSampleComment findPreviousById(long commentId, String reviewerName) {
		String sql = "select processedCommentId, projectName, commenttext, classification, reviewer, reviewerClassification  from significative_sample where reviewer = ? and  processedCommentId < ? order by processedCommentId desc  limit 1";
		SignificativeSampleComment comment = new SignificativeSampleComment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reviewerName);
			preparedStatement.setLong(2, commentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				comment.setProcessedCommentId(resultSet.getLong("processedCommentId"));
				comment.setProjectName(resultSet.getString("projectName"));
				comment.setText(resultSet.getString("commentText"));
				comment.setClassification(resultSet.getString("classification"));
				comment.setReviewer(resultSet.getString("reviewer"));
				comment.setReviewerClassification(resultSet.getString("reviewerClassification"));  
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public SignificativeSampleComment findById(long commentId, String reviewer) {
		String sql = "select processedCommentId, projectName, commenttext, classification, reviewer, reviewerClassification from significative_sample where reviewer = ?  and processedCommentId = ? order by processedCommentId";
		SignificativeSampleComment comment = new SignificativeSampleComment();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, reviewer);
			preparedStatement.setLong(2, commentId);
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()){
				comment.setProcessedCommentId(resultSet.getLong("processedCommentId"));
				comment.setProjectName(resultSet.getString("projectName"));
				comment.setText(resultSet.getString("commentText"));
				comment.setClassification(resultSet.getString("classification"));
				comment.setReviewer(resultSet.getString("reviewer"));
				comment.setReviewerClassification(resultSet.getString("reviewerClassification"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return comment;
	}
	
	public ArrayList<String> getReviewers(){
		ArrayList<String> result = new ArrayList<String>();
		try{
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT DISTINCT(reviewer) FROM significative_sample order by 1");
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				result.add(resultSet.getString("reviewer"));
			}
			return result;
		}catch(SQLException e){
			e.printStackTrace();
		}
		return result;
	}

	public int getSignificativeSampleClassificationProgress(String reviewerName) {
		String totalCommentsSQL = "select count(*) as total from significative_sample where reviewer = ?";
		String classifiedCommentsSQL = "select count(*) as classified from significative_sample where reviewer = ? and reviewerClassification is not null";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(totalCommentsSQL);
			preparedStatement.setString(1, reviewerName);
			ResultSet resultSet = preparedStatement.executeQuery();
			float total = 0;
			while(resultSet.next()){
				total = resultSet.getInt("total");
			}
			PreparedStatement preparedStatement2 = connection.prepareStatement(classifiedCommentsSQL);
			preparedStatement2.setString(1, reviewerName);
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

	public void update(SignificativeSampleComment comment) {
		String sql = "UPDATE significative_sample set projectName = ?, commentText=?, classification=?, reviewer=?, reviewerClassification=? where processedCommentId =? and reviewer=?";
		try{
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, comment.getProjectName());
			preparedStatement.setString(2, comment.getText());
			preparedStatement.setString(3, comment.getClassification());
			preparedStatement.setString(4, comment.getReviewer());
			preparedStatement.setString(5, comment.getReviewerClassification());
			preparedStatement.setLong(6, comment.getProcessedCommentId());
			preparedStatement.setString(7, comment.getReviewer());		
			preparedStatement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}