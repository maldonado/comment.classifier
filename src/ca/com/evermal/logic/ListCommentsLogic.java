package ca.com.evermal.logic;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.CommentDao;
import ca.com.evermal.dao.ProjectDao;
import ca.com.evermal.model.Comment;

public class ListCommentsLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		String projectName = (String) req.getParameter("projectName");
		Connection connection = (Connection) req.getAttribute("connection");
		Boolean getWithoutClassification = Boolean.valueOf(req.getParameter("getWithoutClassification"));
		
		ArrayList<Comment> comments = null;
		if(getWithoutClassification){
			comments = new CommentDao(connection).getCommentsToClassifyByProject(projectName);
		}else{
			comments = new CommentDao(connection).getAllCommentsByProject(projectName);
		}
		
		int progress = new ProjectDao(connection).getClassificationProgress(projectName);
		
		
		req.setAttribute("progress", progress);
		req.setAttribute("comments", comments);
		
		return "list-comments.jsp";
	}
}