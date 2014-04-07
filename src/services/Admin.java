package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class Admin {

	static public JSONObject wordsMapReduce(String password) throws JSONException{
		try
		{
			ArrayList<String> words = core.Admin.wordsMapReduce(password);
			
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

	public static Object PwittsDelete(String password) throws JSONException {
		try
		{
			 core.Admin.pwittsDelete(password);
			
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
