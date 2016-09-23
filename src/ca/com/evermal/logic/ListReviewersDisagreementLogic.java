package ca.com.evermal.logic;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.SignificativeSampleDao;

public class ListReviewersDisagreementLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Connection connection = (Connection) req.getAttribute("connection");
		ArrayList<String> reviewers = new SignificativeSampleDao(connection).getReviewers();
		
		req.setAttribute("reviewers", reviewers);

		return "list-reviewers-disagreement.jsp";
	}
}