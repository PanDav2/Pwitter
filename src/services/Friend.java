package services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;
import services.ServicesTools;

public class Friend {

	static public JSONObject add(String session, int friend_id) throws JSONException{
		try
		{
			core.Friend.add(session,friend_id);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	static public JSONObject remove(String session, int friend_id) throws JSONException{
		try
		{
			core.Friend.remove(session,friend_id);
			JSONObject json = new JSONObject();
			json.put("statut", "ok");
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}

	static public JSONObject find(String session) throws JSONException{
		try
		{
			ArrayList<ArrayList<String>> friends = core.Friend.find(session);
			ArrayList<JSONObject> JSONFriends = new ArrayList<JSONObject>();
			
			JSONObject json = new JSONObject();
			JSONObject user;
			JSONObject JSONFriend;
			JSONObject clone;
	
			json.put("statut", "ok");
			
			for (ArrayList<String> friend : friends) {
				user = new JSONObject();
				JSONFriend = new JSONObject();
				JSONFriend.put("id", friend.get(0));
				JSONFriend.put("email", friend.get(1));
				JSONFriend.put("firstName", friend.get(2));
				JSONFriend.put("lastName", friend.get(3));
				user.put("user", JSONFriend);
				JSONFriends.add(user);
			}
			json.put("friends", new JSONArray(JSONFriends));
			return json;
		}
		catch(CoreException e)
		{
			return(ServicesTools.error(e));
		}
	}
	
	
	
}
