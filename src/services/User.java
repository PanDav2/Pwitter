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
			jsonUser.put("lastName", firstName);
			jsonUser.put("username", firstName);
			jsonUser.put("email", firstName);
			jsonUser.put("password", firstName);
			json.put("user", jsonUser);
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	/*
	static public JSONObject login(String username, String password) throws JSONException {
		try
		{
			core.User.login( username, password);
			return ServicesTools.ok();
			//return core.JsonFactory.getJson();
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
		
	}
	static public JSONObject logout(String key) throws JSONException {
		try
		{
			core.User.logout( key );
			return tools.Json.ok();
			//return core.JsonFactory.getJson();
		}
		catch(CoreException e)
		{
			return(tools.Json.error(101,"BD problem"));
		}
		
	}
	*/
	
	
	
}
