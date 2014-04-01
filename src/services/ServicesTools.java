package services;

import org.json.JSONException;
import org.json.JSONObject;

import core.CoreException;


public class ServicesTools {

	public static JSONObject error(String msg, int id) throws JSONException {
		JSONObject json = new JSONObject();
		JSONObject jsonError = new JSONObject();
		json.put("status", "error");
		jsonError.put("code", String.valueOf(id));
		jsonError.put("msg", msg);
		json.put("error", jsonError);
		return json;
	}
	
	public static JSONObject ok() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("status", "ok");
		return json;
	}

	public static JSONObject error(CoreException e) throws JSONException {
		return error(e.getMsg(), e.getId());
	}
	
}
