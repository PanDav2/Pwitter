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
/* SERVLET EXCEPTION */
			case 0: msg = "Wrong Arguments";
					break;
			case 1: msg = "test";
					break;
/* SERVICES EXCEPTION */
			case 1000: msg = "Wrong username";
					break;
			case 11: msg = "Wrong password";
					break;
			case 9: msg = "Your session has expired. Please log in again";
					break;
/* CORE EXCEPTION */
			case 2000: msg = "Wrong username";
			break;
			case 2001: msg = "Wrong password";
			break;
			default: msg = "No information available, please contact admin";
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
