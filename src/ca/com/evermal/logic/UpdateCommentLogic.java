package ca.com.evermal.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.CommentDao;
import ca.com.evermal.model.Comment;

public class UpdateCommentLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		long commentId =  Long.valueOf(req.getParameter("commentId"));
		String classification = req.getParameter("classification");
		Connection connection = (Connection) req.getAttribute("connection");
		
		CommentDao dao = new CommentDao(connection);
		Comment comment = dao.findById(commentId);
		comment.setClassification(classification);
		dao.update(comment);
		
		req.setAttribute("projectName", comment.getProjectName());

		return "updated-comment.jsp";
	}
}