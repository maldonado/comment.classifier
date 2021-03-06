package ca.com.evermal.logic;

import java.sql.Connection;

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
		
		Comment comment = null;
		if(getWithoutClassification){
			comment = new CommentDao(connection).getCommentsToClassifyByProject(projectName);
		}else{
			comment = new CommentDao(connection).getAllCommentsByProject(projectName);
		}
		
		int progress = new ProjectDao(connection).getClassificationProgress(projectName);
		
		
		req.setAttribute("progress", progress);
		req.setAttribute("comment", comment);
		req.setAttribute("projectName", projectName);
		
		return "list-comments.jsp";
	}
}