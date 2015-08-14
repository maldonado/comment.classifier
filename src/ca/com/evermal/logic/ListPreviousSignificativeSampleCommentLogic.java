package ca.com.evermal.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.SignificativeSampleDao;
import ca.com.evermal.model.SignificativeSampleComment;

public class ListPreviousSignificativeSampleCommentLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		String reviewerName =  req.getParameter("reviewerName");
		long commentId =  Long.valueOf(req.getParameter("commentId"));
		Connection connection = (Connection) req.getAttribute("connection");
		
		SignificativeSampleDao dao = new SignificativeSampleDao(connection);
		SignificativeSampleComment comment = dao.findPreviousById(commentId, reviewerName);		
	
		int progress = dao.getSignificativeSampleClassificationProgress(reviewerName);
		
		req.setAttribute("progress", progress);
		req.setAttribute("comment", comment);
		req.setAttribute("reviewerName", reviewerName);
		
		return "list-significative-sample.jsp";
	}
}