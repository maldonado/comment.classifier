package ca.com.evermal.controller;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.CommentDao;
import ca.com.evermal.model.Comment;

public class UpdateCommentController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		Comment comment = (Comment) req.getAttribute("comment");
		String projectName = (String) req.getAttribute("projectName");
		Connection connection = (Connection) req.getAttribute("connection");
		
		new CommentDao(connection).update(comment);
		
		req.setAttribute("getWithoutClassification", false);
		req.setAttribute("projectName", projectName);

		return "list-comments.jsp";
	}
}