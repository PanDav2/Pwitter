package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;

public class Pwitt {

	static public JSONObject send(String session, String content) throws JSONException{
		try
		{
			core.Pwitt.send(session,content);
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
			ArrayList<JSONObject> JSONPwitts = new ArrayList<JSONObject>();
			
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			
			json.put("date", tools.Time.getCurrentTimestamp());
			
			if(session != "")
			{
				json.put("user_id", core.User.isAuthentified(session));
				
				if(dofriends != "")
					json.put("dofriends", 0);
				else
					json.put("dofriends", dofriends);
			}
			
			if(words != "")
				json.put("words", words);
			
			for(String pwitt : pwitts)
				JSONPwitts.add(new JSONObject(pwitt));
			json.put("pwitts", new JSONArray(JSONPwitts));
			
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

}
