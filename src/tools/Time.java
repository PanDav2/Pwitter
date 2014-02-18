package tools;

import java.sql.Timestamp;
import java.util.Date;

//import java.sql.Timestamp;

public class Time {
	 
    public static int getCurrentTime()
    {
    	Date date= new Date();
    	return (int)date.getTime();
    }
    
    public static Timestamp getCurrentTimestamp()
    {
    	Date date= new Date();
    	return new Timestamp(date.getTime());
    }
	
}
