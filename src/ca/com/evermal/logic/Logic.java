package ca.com.evermal.logic;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Logic {
	
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}
