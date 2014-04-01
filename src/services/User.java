package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class User {

	static public JSONObject register(String firstname, String lastname, String email, String password) throws JSONException{
		try
		{
			int id = core.User.register(firstname, lastname, email, password);
			JSONObject json = new JSONObject();
			JSONObject jsonUser = new JSONObject();
			json.put("statut", "ok");
			jsonUser.put("id", id);
			jsonUser.put("firstname", firstname);
			jsonUser.put("lastname", lastname);
			jsonUser.put("email", email);
			jsonUser.put("password", password);
			json.put("user", jsonUser);
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	static public JSONObject login(String email, String password) throws JSONException {
		try
		{
			String session = core.User.login(email, password);
			JSONObject json = new JSONObject();
			JSONObject jsonUser = new JSONObject();
			json.put("statut", "ok");
			jsonUser.put("email", email);
			jsonUser.put("password", password);
			jsonUser.put("session", session);
			json.put("user", jsonUser);
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
		
	}
	
	static public JSONObject logout(String session) throws JSONException {
		try
		{
			core.User.logout(session);
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
