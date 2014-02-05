package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;

public class User {

	static public JSONObject create(String firstName, String lastName, String username, String email, String password) throws JSONException {
		try
		{
			core.User.create(firstName, lastName, username, email, password);
			return tools.Json.ok();
			//return core.JsonFactory.getJson();
		}
		catch(CoreException e)
		{
			return(tools.Json.error(101,"BD problem"));
		}
	}
	
	static public JSONObject login(String username, String password) throws JSONException {
		try
		{
			core.User.login( username, password);
			return tools.Json.ok();
			//return core.JsonFactory.getJson();
		}
		catch(CoreException e)
		{
			return(tools.Json.error(101,"BD problem"));
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
	
	
	
	
}
