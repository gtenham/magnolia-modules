package nl.magnodom.resources;

import info.magnolia.cms.beans.config.URI2RepositoryManager;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MiniflyServlet extends HttpServlet {

	private static final long serialVersionUID = -3696064262165464455L;

	private URI2RepositoryManager uri2RepositoryManager;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
	
	}
}
