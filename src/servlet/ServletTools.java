package servlet;

public class ServletTools {

	public static String JSONError() {
		return "{\"status\":\"error\",\"error_code\":\"0\",\"error_msg\":\"JSONError.\"}";
	}
	
	public static String ArgError() {
		return "{\"status\":\"error\",\"error_code\":\"1\",\"error_msg\":\"Au moins un argument nein valide.\"}";
	}
}
