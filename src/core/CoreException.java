package core;

public class CoreException extends Exception {
	
	private String msg;
	private int id;
	
	public CoreException (String msg, int id) {
		this.msg = msg;
		this.id = id;
	}
	
	public CoreException (int id) {
		switch (id) {
			case 0: msg = "Wrong Arguments";
					break;
			case 1: msg = "test";
					break;
			case 9: msg = "Your session has expired. Please log in again";
					break;
			case 10: msg = "Database PB";
					break;
			default: msg = "default";
					 break;
		}
		this.id = id;
	}
	
	public String getMsg(){
		return msg;
	}
	public int getId(){
		return id;
	}
	
	
}
