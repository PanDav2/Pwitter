package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class Admin {

	static public JSONObject WordsMapReduce(String password) throws JSONException{
		try
		{
			ArrayList<String> words = core.Admin.WordsMR(password);
			
			JSONObject json = new JSONObject();
			
			json.put("statut", "ok");
			
			json.put("words",  new JSONArray(words));
			
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	
	
	
	
}
