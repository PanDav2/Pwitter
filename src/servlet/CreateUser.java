package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class CreateUser extends HttpServlet{
	
	//http://li328.lip6.fr:8280/CADENE_PANOU/user/create?username=Tamazy&password=azerty&firstName=Remi&lastName=Cadene&email=remicadene@laposte.net
	//2303897
	
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
				/*JSONObject json = new JSONObject();
				while(resultat.next()){
					int id = resultat.getInt("id");
					String name = resultat.getString("username");
					json.put("id", id);
					json.put("name", name);
				}*/
			} catch (JSONException e) {
				//e.printStackTrace();
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
