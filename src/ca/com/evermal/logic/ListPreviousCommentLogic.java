package ca.com.evermal.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.CommentDao;
import ca.com.evermal.dao.ProjectDao;
import ca.com.evermal.model.Comment;

public class ListPreviousCommentLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		String projectName =  req.getParameter("projectName");
		long commentId =  Long.valueOf(req.getParameter("commentId"));
		Connection connection = (Connection) req.getAttribute("connection");
		
		CommentDao dao = new CommentDao(connection);
		Comment comment = dao.findPreviousById(commentId, projectName);		
	
		int progress = new ProjectDao(connection).getClassificationProgress(projectName);
		
		req.setAttribute("progress", progress);
		req.setAttribute("comment", comment);
		req.setAttribute("projectName", projectName);
		
		return "list-comments.jsp";
	}
}