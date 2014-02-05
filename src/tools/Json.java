package tools;

import org.json.JSONException;
import org.json.JSONObject;

public class Json {

	//private static JSONObject json = new JSONObject();
	
	public static JSONObject ok() throws JSONException {
		JSONObject json = new JSONObject();
		json.put("status", "ok");
		return json;
	}
	
	/*public static void put(String key, Object value) throws JSONException {
		json = new JSONObject();
		json.put(key,value);
	}
	
	public static JSONObject getJson(){
		return json;
	}*/
	
	public static JSONObject error(int id, String msg) throws JSONException {
		JSONObject json = new JSONObject();
		json.put("status", "error");
		json.put("error_code", String.valueOf(id));
		json.put("error_msg", msg);
		return json;
	}
	
}
