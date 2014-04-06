package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;

public class Pwitt {

	static public JSONObject add(String session, String content) throws JSONException{
		try
		{
			core.Pwitt.add(session,content);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}
	
	static public JSONObject find(String session,String words,String dofriends) throws JSONException{
		try
		{
			ArrayList<String> pwitts = core.Pwitt.find(session,words,dofriends);
			
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			
			json.put("date", tools.Time.getCurrentTimestamp());
			
			if(session != "")
			{
				json.put("author", core.User.getEmail(session));
				
				if(dofriends != "")
					json.put("dofriends", 0);
				else
					json.put("dofriends", dofriends);
			}
			
			if(words != "")
				json.put("words", words);
			
			json.put("pwitts", new JSONArray(pwitts));
			
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

}
