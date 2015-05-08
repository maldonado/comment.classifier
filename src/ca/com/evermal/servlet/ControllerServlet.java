package ca.com.evermal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.logic.Logic;

@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789134590989356419L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("logic");
		String className = "ca.com.evermal.logic." + parameter;

		try {
			Class<?> clazz = Class.forName(className);
			Logic controller = (Logic) clazz.newInstance();
			String page = controller.execute(request, response);

			request.getRequestDispatcher(page).forward(request, response);

		} catch (Exception e) {
			throw new ServletException("Exception during the process", e);
		}
	}
}