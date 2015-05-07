package ca.com.evermal.controller;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.CommentDao;
import ca.com.evermal.model.Comment;

public class ListCommentsController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		String projectName = (String) req.getAttribute("projectName");
		Connection connection = (Connection) req.getAttribute("connection");
		Boolean getWithoutClassification = (Boolean) req.getAttribute("getWithoutClassification");
		
		ArrayList<Comment> comments = null;
		if(getWithoutClassification){
			comments = new CommentDao(connection).getCommentsToClassifyByProject(projectName);
		}else{
			comments = new CommentDao(connection).getAllCommentsByProject(projectName);
		}
		
		req.setAttribute("comments", comments);
		return "list-comments.jsp";
	}
}