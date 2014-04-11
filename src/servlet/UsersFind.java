package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class UsersFind extends HttpServlet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("session")) {
			
			String session =  req.getParameter("session");
			
			String words = "";
			
			if(req.getParameterMap().containsKey("words")) {
				words = req.getParameter("words");
			}
			
			//String[] ArrayWords = words.split(" ");
			//resp.getWriter().println(ArrayWords.length + " : " + ArrayWords[0] );
			
			try {
				resp.getWriter().println(services.Users.find(session,words).toString());
			} catch (JSONException e) {
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
