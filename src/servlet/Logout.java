package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class Logout extends HttpServlet{
	
	//http://li328.lip6.fr:8280/CADENE_PANOU/users/logout?sessionKey=
	
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("sessionKey")) {
		
			String sessionKey =  req.getParameter("sessionKey");
		
			try {
				resp.getWriter().println(services.User.logout(sessionKey).toString());
			} catch (JSONException e) {
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
