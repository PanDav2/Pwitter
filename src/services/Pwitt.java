package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;

public class Pwitt {

	static public JSONObject add(String session, String title, String content) throws JSONException{
		try
		{
			core.Pwitt.add(session,title,content);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}
	
	static public JSONObject find(String session) throws JSONException{
		try
		{
			String d = core.Pwitt.find(session);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			json.put("debug", d);
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

}
