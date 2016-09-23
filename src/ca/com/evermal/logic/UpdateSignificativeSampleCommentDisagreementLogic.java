package ca.com.evermal.logic;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.SignificativeSampleDao;
import ca.com.evermal.model.SignificativeSampleComment;

public class UpdateSignificativeSampleCommentDisagreementLogic implements Logic{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp)	throws Exception {
		
		long commentId =  Long.valueOf(req.getParameter("commentId"));
		String classification = req.getParameter("classification");
		String reviewer = req.getParameter("reviewer");
		Connection connection = (Connection) req.getAttribute("connection");
		
		SignificativeSampleDao dao = new SignificativeSampleDao(connection);
		SignificativeSampleComment comment = dao.findById(commentId, reviewer);
		comment.setReviewerClassification(classification);
		comment.setReviewed(true);
		dao.update(comment);
		
		req.setAttribute("reviewerName", comment.getReviewer());

		return "updated-significative-sample-comment-disagreement.jsp";
	}
}