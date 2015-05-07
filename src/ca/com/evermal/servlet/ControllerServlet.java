package ca.com.evermal.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.controller.Controller;

@SuppressWarnings("rawtypes")
@WebServlet("/mvc")
public class ControllerServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6789134590989356419L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("controller");
		String nomeDaClasse = "ca.com.evermal.controller." + parameter;

		try {
			Class clazz = Class.forName(nomeDaClasse);
			Controller controller = (Controller) clazz.newInstance();
			String page = controller.execute(request, response);

			request.getRequestDispatcher(page).forward(request, response);

		} catch (Exception e) {
			throw new ServletException(
					"Exception during the process", e);
		}
	}
}