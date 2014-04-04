package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class Server {

	static public JSONObject mapReduce(String password) throws JSONException{
		try
		{
			String d = core.Server.mapReduce(password);
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
