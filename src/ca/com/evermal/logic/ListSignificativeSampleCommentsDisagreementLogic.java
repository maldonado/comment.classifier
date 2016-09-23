package ca.com.evermal.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.SignificativeSampleDao;
import ca.com.evermal.model.SignificativeSampleComment;

public class ListSignificativeSampleCommentsDisagreementLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		String reviewerName = (String) req.getParameter("reviewerName");
		Connection connection = (Connection) req.getAttribute("connection");
		
		SignificativeSampleComment comment = null;
		
		comment = new SignificativeSampleDao(connection).getSignificativeSampleCommentsWithDisagreementByReviewer(reviewerName);
		
//		int progress = new SignificativeSampleDao(connection).getSignificativeSampleClassificationProgress(reviewerName);
		
//		req.setAttribute("progress", progress);
		req.setAttribute("comment", comment);
		req.setAttribute("reviewerName", reviewerName);
		
		return "list-significative-sample-disagreement.jsp";
	}
}