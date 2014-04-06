package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class PwittsFind extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		String session = "";
		String words = "";
		String dofriends = "";
		
		if(req.getParameterMap().containsKey("session")) {
			session = req.getParameter("session");
		}
		if(req.getParameterMap().containsKey("words")) {
			words = req.getParameter("words");
		}
		if(req.getParameterMap().containsKey("dofriends")) {
			dofriends = req.getParameter("dofriends");
		}
		
			
		try {
			resp.getWriter().println(services.Pwitt.find(session,words,dofriends).toString());
		} catch (JSONException e) {
			resp.getWriter().println(ServletTools.JSONError());
		}
			
	
	
	}
	

}
