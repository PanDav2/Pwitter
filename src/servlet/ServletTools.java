package servlet;

public class ServletTools {

	public static String JSONError() {
		return "{\"status\":\"error\",\"error\": {\"code\":\"-1\",\"msg\":\"JSONError.\"}}";
	}
	
	public static String ArgError() {
		return "{\"status\":\"error\",\"error\": {\"code\":\"-2\",\"msg\":\"ArgError.\"}}";
	}
}
