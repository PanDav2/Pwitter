package tools;

import java.net.UnknownHostException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class MongoDB {
	
	public static DB getConnection() throws UnknownHostException, MongoException{
		Mongo m = new Mongo("132.227.201.129",27130);
		return m.getDB("mydb");
	}
	
}
