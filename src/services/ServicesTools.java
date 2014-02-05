package services;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesTools {

	public static JSONObject error(String msg, int id) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("status", "error");
		json.put("error_code", String.valueOf(id));
		json.put("error_msg", msg);
		return json;
	}
}
