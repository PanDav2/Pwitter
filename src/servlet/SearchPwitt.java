package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

public class SearchPwitt extends HttpServlet{
	
	@Override
	protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws IOException{
		
		resp.setContentType("plain/text");
		
		if(req.getParameterMap().containsKey("sessionKey")) {
			String sessionKey = req.getParameter("sessionKey");
			
			try {
				resp.getWriter().println(services.Pwitt.search(sessionKey).toString());
			} catch (JSONException e) {
				//e.printStackTrace();
				resp.getWriter().println("{\"status\":\"error\",\"error_code\":\"0\",\"error_msg\":\"JSONError.\"}");
			}
			
		} else {
				resp.getWriter().println("{\"status\":\"error\",\"error_code\":\"1\",\"error_msg\":\"Au moins un argument nein valide.\"}");
		}
	
	}
	

}
