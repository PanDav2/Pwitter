package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class Friend {

	static public JSONObject add(String session, int friend_id) throws JSONException{
		try
		{
			core.Friend.add(session,friend_id);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	static public JSONObject remove(String session, int friend_id) throws JSONException{
		try
		{
			core.Friend.remove(session,friend_id);
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
