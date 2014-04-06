package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class UserRegister extends HttpServlet{
	
	//http://li328.lip6.fr:8280/CADENE_PANOU/user/register?username=Tamazy&password=azerty&firstName=Remi&lastName=Cadene&email=remicadene@laposte.net
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("password") &&
		   req.getParameterMap().containsKey("firstname") &&
		   req.getParameterMap().containsKey("email") &&
		   req.getParameterMap().containsKey("lastname")) {
		
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String firstname =  req.getParameter("firstname");
			String lastname = req.getParameter("lastname");
			email = email.toLowerCase();
			firstname = firstname.toLowerCase();
			lastname = lastname.toLowerCase();
			firstname = Character.toUpperCase(firstname.charAt(0)) + firstname.substring(1);
			lastname = Character.toUpperCase(firstname.charAt(0)) + lastname.substring(1);
			
			try {
				resp.getWriter().println(services.User.register(firstname, lastname, email, password).toString());
			} catch (JSONException e) {
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
