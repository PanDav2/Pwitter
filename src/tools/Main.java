package tools;

import java.io.UnsupportedEncodingException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class Main {

	public static void main(String[] args) {
		
		try {
			String myUserName = "Cadene_Panou";
			String myPassword = "Cadene_Panou";
			Mongo m = new Mongo("132.227.201.129",27130);
			DB db = m.getDB("mydb");
		} catch (UnknownHostException | MongoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
