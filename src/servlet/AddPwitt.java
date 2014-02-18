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
		
		if(req.getParameterMap().containsKey("sessionKey") &&
		   req.getParameterMap().containsKey("content")) {
			
			String sessionKey = req.getParameter("sessionKey");
			String content = req.getParameter("content");
			String title = null;
			
			if(req.getParameterMap().containsKey("title"))
				title = req.getParameter("title");
		
			try {
				resp.getWriter().println(services.Pwitt.add(sessionKey,title,content).toString());
			} catch (JSONException e) {
				//e.printStackTrace();
				resp.getWriter().println("{\"status\":\"error\",\"error_code\":\"0\",\"error_msg\":\"JSONError.\"}");
			}
			
		} else {
				resp.getWriter().println("{\"status\":\"error\",\"error_code\":\"1\",\"error_msg\":\"Au moins un argument nein valide.\"}");
		}
	
	}
	

}
