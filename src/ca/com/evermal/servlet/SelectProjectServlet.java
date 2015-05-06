package ca.com.evermal.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.CommentClassDao;

@WebServlet("/selectProject")
public class SelectProjectServlet extends 	HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4541687923178443727L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse  response) throws ServletException, IOException {
		Connection connection = (Connection) request.getAttribute("connection");
		new CommentClassDao(connection).getProjects();
		
		PrintWriter out = response.getWriter();

	    out.println("<html>");
	    out.println("<head>");
	    out.println("<title>Primeira Servlet</title>");
	    out.println("</head>");
	    out.println("<body>");
	    out.println("<h1>Oi mundo Servlet!</h1>");
	    out.println("</body>");
	    out.println("</html>");
	}

}
