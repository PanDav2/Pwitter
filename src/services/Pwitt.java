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
	
	// TODO rajouter le pwitt.id
	static public JSONObject find(String session,String words,String dofriends,String doallusers) throws JSONException{
		try
		{
			ArrayList<String> pwitts = core.Pwitt.find(session,words,dofriends,doallusers);
			ArrayList<JSONObject> JSONPwitts = new ArrayList<JSONObject>();
			
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			
			json.put("date", tools.Time.getCurrentTimeUnix());
			
			if(session.length() != 0)
			{
				json.put("user_id", core.User.isAuthentified(session));
				
				if(dofriends.length() != 0)
					json.put("dofriends", true);
				
				if(doallusers.length() != 0)
					json.put("doallusers", true);
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
