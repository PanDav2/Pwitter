package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class CreateUser extends HttpServlet{
	
	//http://localhost:8080/CADENE_PANOU/user/create?username="Tamazy"&password="azerty"&firstName="Remi"&lastName="Cadene"&email="remicadene@laposte.net"
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("username") &&
		   req.getParameterMap().containsKey("password") &&
		   req.getParameterMap().containsKey("firstName") &&
		   req.getParameterMap().containsKey("email") &&
		   req.getParameterMap().containsKey("lastName")) {
		
			String username =  req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String firstName =  req.getParameter("firstName");
			String lastName = req.getParameter("lastName");
		
			try {
				resp.getWriter().println(services.User.create(firstName, lastName, username, email, password).toString());
			} catch (JSONException e) {
				//e.printStackTrace();
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
