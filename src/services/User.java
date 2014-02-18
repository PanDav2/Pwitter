package services;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class User {

	static public JSONObject create(String firstName, String lastName, String username, String email, String password) throws JSONException{
		try
		{
			int id = core.User.create(firstName, lastName, username, email, password);
			JSONObject json = new JSONObject();
			JSONObject jsonUser = new JSONObject();
			json.put("statut", "ok");
			jsonUser.put("id", id);
			jsonUser.put("firstName", firstName);
			jsonUser.put("lastName", lastName);
			jsonUser.put("username", username);
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

	static public JSONObject login(String username, String password) throws JSONException {
		try
		{
			String sessionKey = core.User.login(username, password);
			JSONObject json = new JSONObject();
			JSONObject jsonUser = new JSONObject();
			json.put("statut", "ok");
			jsonUser.put("username", username);
			jsonUser.put("password", password);
			jsonUser.put("sessionKey", sessionKey);
			json.put("user", jsonUser);
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
		
	}
	
	static public JSONObject logout(String sessionKey) throws JSONException {
		try
		{
			core.User.logout(sessionKey);
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
