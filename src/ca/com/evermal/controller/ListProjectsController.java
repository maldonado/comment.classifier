package ca.com.evermal.controller;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.com.evermal.dao.ProjectDao;

public class ListProjectsController implements Controller{

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

		Connection connection = (Connection) req.getAttribute("connection");
		ArrayList<String> projects = new ProjectDao(connection).getProjects();

		req.setAttribute("projects", projects);

		return "list-projects.jsp";
	}
}