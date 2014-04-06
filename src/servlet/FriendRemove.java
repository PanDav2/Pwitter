package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class FriendRemove extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("session") &&
		   req.getParameterMap().containsKey("friend_id")) {
		
			String session =  req.getParameter("session");
			int friend_id = Integer.parseInt(req.getParameter("friend_id"));
		
			try {
				resp.getWriter().println(services.Friend.remove(session,friend_id).toString());
			} catch (JSONException e) {
				resp.getWriter().println(ServletTools.JSONError());
			}
			
		} else {
				resp.getWriter().println(ServletTools.ArgError());
		}
	
	}
	

}
