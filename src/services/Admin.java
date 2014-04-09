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
			ArrayList<JSONObject> JSONWords = new ArrayList<JSONObject>();
			
			JSONObject json = new JSONObject();
			
			json.put("statut", "ok");
			
			for(String word : words){
				JSONWords.add(new JSONObject(word));
			}
			json.put("words",  new JSONArray(JSONWords));
			
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}
	
	static public JSONObject freqsMapReduce(String password) throws JSONException{
		try
		{
			ArrayList<String> words = core.Admin.freqsMapReduce(password);
			ArrayList<JSONObject> JSONWords = new ArrayList<JSONObject>();
			
			JSONObject json = new JSONObject();
			
			json.put("statut", "ok");
			
			for(String word : words){
				JSONWords.add(new JSONObject(word));
			}
			json.put("words",  new JSONArray(JSONWords));
			
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	public static Object pwittsDrop(String password) throws JSONException {
		try
		{
			core.Admin.pwittsDrop(password);
			
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
