package tools;

import java.sql.Timestamp;
import java.util.Date;

//import java.sql.Timestamp;

public class Time {
	 
	private static long getCurrentTimeMillis(){
		return System.currentTimeMillis();
	}
	
    public static int getCurrentTimeUnix()
    {
    	return  (int) (getCurrentTimeMillis() / 1000L);
    }
    
    public static Timestamp getCurrentTimestamp()
    {
    	return new Timestamp(getCurrentTimeMillis());
    }
	
}
