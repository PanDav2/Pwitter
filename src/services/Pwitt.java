package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;

public class Pwitt {

	static public JSONObject add(String sessionKey, String title, String content) throws JSONException{
		try
		{
			core.Pwitt.add(sessionKey,title,content);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

}
