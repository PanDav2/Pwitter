package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class LoginUser extends HttpServlet{
	
	//http://li328.lip6.fr:8280/CADENE_PANOU/users/login?username=Tamazy&password=azerty
	
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("email") &&
		   req.getParameterMap().containsKey("password")) {
		
			String email =  req.getParameter("email");
			String password = req.getParameter("password");
			email = email.toLowerCase();
		
			try {
				resp.getWriter().println(services.User.login(email, password).toString());
			} catch (JSONException e) {
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
