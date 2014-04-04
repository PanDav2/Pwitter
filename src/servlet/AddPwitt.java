package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class AddPwitt extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("session") &&
		   req.getParameterMap().containsKey("content")) {
			
			String session = req.getParameter("session");
			String content = req.getParameter("content");
			String title = null;
			
			if(req.getParameterMap().containsKey("title"))
				title = req.getParameter("title");
		
			try {
				resp.getWriter().println(services.Pwitt.add(session,title,content).toString());
			} catch (JSONException e) {
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	
	}
	

}
